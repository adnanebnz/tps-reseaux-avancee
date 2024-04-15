package EXO5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class C {
    public static void main(String[] args) {
        File fichier = new File("./EXO5/test1.txt");
        try {
            FileWriter flotEcriture = new FileWriter(fichier);
            for (int i = 49; i <= 57; i++)
                flotEcriture.write(i);
            flotEcriture.close();
        } catch (IOException e) {
        }
    }
}