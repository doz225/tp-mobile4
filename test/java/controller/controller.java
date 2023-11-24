package controller;
import model.patient;
public class controller {

    // Ajouter un attribut statique pour l'instance unique (Singleton)
    private static controller instance;
    // Attribut représentant le modèle (Patient)
    private patient patient;
    // Constructeur privé pour le Singleton
    public controller() {
        // Initialisation du modèle
        patient = new patient("", 0, 0.0);
    }

    // Méthode statique et finale pour obtenir l'instance unique (Singleton)
    public static synchronized controller getInstance() {
        if (instance == null) {
            instance = new controller();
        }
        return instance;
    }

    // Méthode pour créer un patient en initialisant le modèle avec les propriétés
    // fournies par l'utilisateur
    public void createPatient(String nom, int age, double niveauGlycemie) {
        patient = new patient(nom, age, niveauGlycemie);
    }

    // Méthode pour obtenir la réponse de l'analyse du niveau de glycémie
    public String getResponse() {
        // Appel de la méthode de calcul dans le modèle (Patient)
        return patient.calculer();
    }
}