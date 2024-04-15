package EXO2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

class EcrirePersonne {
    public static void main(String[] args) {
        List<Personne> personnes = new ArrayList<>();
        personnes.add(new Personne("Doe", "John", 30));
        personnes.add(new Personne("Doe", "Jane", 25));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./EXO2/personnes.txt"))) {
            oos.writeObject(personnes);
            System.out.println("Liste de personnes sauvegardée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
