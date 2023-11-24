package vue;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import com.example.mesure_glycemie.R;
import controller.controller;
public class MainActivity extends AppCompatActivity {
    private TextView tvage;
    private TextView tvresultat;
    private SeekBar sbage;
    private RadioButton rboui;
    private RadioButton rbnon;
    private EditText etvaleur;
    private Button btnConsulter;
    // Ajouter une instance de la classe Controller
    private controller controller;
    private void init() {
        etvaleur = (EditText) findViewById(R.id.ETValMes);
        sbage = (SeekBar) findViewById(R.id.SbAge);
        tvage = (TextView) findViewById(R.id.TVAge);
        rboui = (RadioButton) findViewById(R.id.rbtOui);
        rbnon = (RadioButton) findViewById(R.id.rbtNon);
        btnConsulter = (Button) findViewById(R.id.btnConsulter);
        tvresultat = (TextView) findViewById(R.id.tvresultat);

        // Initialiser l'instance du contrôleur
        controller = new controller();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                tvage.setText("Votre âge : " + progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}

            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }

            private void calculer(View v) {
                // Récupérer les valeurs de l'interface utilisateur
                int age;
                float valeur;
                boolean verifAge = false, verifValeur = false;

                // Vérifier l'âge
                if (sbage.getProgress() != 0)
                    verifAge = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez vérifier votre âge",
                            Toast.LENGTH_SHORT).show();

                // Vérifier la valeur mesure
                if (!etvaleur.getText().toString().isEmpty())
                    verifValeur = true;
                else
                    Toast.makeText(MainActivity.this,
                            "Veuillez vérifier votre valeur mesure", Toast.LENGTH_LONG).show();

                // Si les vérifications sont réussies
                if (verifAge && verifValeur) {
                    // Récupérer les valeurs de l'interface utilisateur
                    String contenuTexte = etvaleur.getText().toString();
                    double niveauGlycemie = Double.parseDouble(contenuTexte);
                    int contenuAge = sbage.getProgress();
                    boolean estAJean = rboui.isChecked();

                    // Utiliser le contrôleur pour créer un patient avec les valeurs fournies
                    controller.createPatient("", contenuAge, niveauGlycemie);

                    // Utiliser le contrôleur pour obtenir la réponse de l'analyse du niveau de glycémie
                    String message = controller.getResponse();

                    // Afficher le résultat à l'utilisateur
                    tvresultat.setText(message);
                }
            }
        });
    }
}