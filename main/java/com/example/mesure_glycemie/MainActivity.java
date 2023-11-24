package com.example.mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Question1
    private TextView TVAge;
    private SeekBar sbAge;
    private RadioButton BtnOui;
    private RadioButton BtnNon;
    private EditText ETValMes;
    private Button BtnConsulter;
    private TextView text;

    @SuppressLint("WrongViewCast")
    public MainActivity() {
        text = findViewById(R.id.rg5);
    }

    //Question 2
    private void init() {
        TVAge = findViewById(R.id.TVAge);
        sbAge = findViewById(R.id.SbAge);
        BtnOui = findViewById(R.id.rbtOui);
        BtnNon = findViewById(R.id.BtnNon);
        ETValMes = findViewById(R.id.ETValMes);
        BtnConsulter = findViewById(R.id.BtnConsulter);
        //Question 3
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                // affichage dans le Log de Android studio pour voir les changements détectés sur le SeekBar ..
                TVAge.setText("Votre âge : " + progress);
                // Mise à jour du TextView par la valeur: progress à chaque changement.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        BtnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = sbAge.getProgress();
                System.out.println("age =" + age);
                String valmesure = (ETValMes.getText().toString());
                System.out.println("val =" + valmesure);
                if (age == 0 && valmesure.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Age and valeur mesure invalide", Toast.LENGTH_SHORT).show();
                } else if (age == 0) {
                    Toast.makeText(getApplicationContext(), "Age invalide", Toast.LENGTH_SHORT).show();
                } else if (valmesure.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Valeur mesure invalide", Toast.LENGTH_SHORT).show();
                } else {
                    float valMes = Float.parseFloat(valmesure);
                    boolean jeun = BtnOui.isChecked();
                    if (jeun) {
                        if (age > 13) {
                            if (valMes >= 5.0 && valMes <= 7.2) {
                                text.setText("Niveau de glycémie est normale");
                            } else if (valMes < 5.0) {
                                text.setText("Niveau de glycémie est trop bas");
                            } else if (valMes > 7.2) {
                                text.setText("Niveau de glycémie est trop élevée");
                            }
                        } else if (age >= 6 && age <= 12) {
                            if (valMes >= 5.0 && valMes <= 10.0) {
                                text.setText("Niveau de glycémie est normale");
                            } else {
                                text.setText("Niveau de glycémie est trop bas");
                            }
                        } else if (age < 6) {
                            if (valMes >= 5.5 && valMes <= 10.0) {
                                text.setText("Niveau de glycémie est normale");
                            } else {
                                text.setText("Niveau de glycémie est trop bas");
                            }
                        }
                    } else {
                        if (valMes < 10.5) {
                            text.setText("Niveau de glycémie est normale");
                        } else {
                            text.setText("Niveau de glycémie est trop élevée");
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}