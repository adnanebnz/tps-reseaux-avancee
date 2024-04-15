package EXO2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

class LirePersonne {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<Personne> personnes = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./EXO2/personnes.txt"))) {
            personnes = (List<Personne>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Personne personne : personnes) {
            System.out.println(personne.toString());
        }
    }
}