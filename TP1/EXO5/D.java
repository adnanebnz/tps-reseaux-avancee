package EXO5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class D {
    public static void main(String[] args) {
        File fichier = new File("./EXO5/test1.txt");
        try {
            FileWriter flotEcriture = new FileWriter(fichier);
            for (int i = 1; i <= 9; i++) {
                flotEcriture.write(i + "");
            }
            flotEcriture.close();
        } catch (IOException e) {
        }
    }
}
