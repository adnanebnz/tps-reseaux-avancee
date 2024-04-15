package EXO2;

import java.io.*;

class Personne implements Serializable {
    private static final long serialVersionUID = 1L;
    public String nom;
    transient public String prenom;
    transient public int age;

    public Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                '}';
    }
}