package model;
public class patient {
    // Attributs représentant les données de l'application
    private String nom;
    private int age;
    private double niveauGlycemie;

    // Constructeur de la classe
    public patient(String nom, int age, double niveauGlycemie) {
        this.nom = nom;
        this.age = age;
        this.niveauGlycemie = niveauGlycemie;
    }
    // Getters pour accéder aux attributs
    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public double getNiveauGlycemie() {
        return niveauGlycemie;
    }

    // Méthode pour calculer le niveau de glycémie
    public String calculer() {
        // Logique de calcul du niveau de glycémie
        // Retourner le résultat sous forme de chaîne de caractères par exemple
        if (niveauGlycemie < 70) {
            return "Hypoglycémie";
        } else if (niveauGlycemie >= 70 && niveauGlycemie <= 130) {
            return "Niveau de glycémie normal";
        } else {
            return "Hyperglycémie";
        }
    }
}