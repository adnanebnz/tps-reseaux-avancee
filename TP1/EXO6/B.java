package EXO6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class B {

    public static void main(String[] args) {
        File fichier = new File("./EXO6/test3.txt");
        try {
            FileWriter flotEcriture = new FileWriter(fichier);
            String s = "mirsdgl";

            for (int i = 0; i < s.length() / 2; i++) {
                flotEcriture.write(" ".repeat(i));
                flotEcriture.write(s.substring(i, 7 - i));
                flotEcriture.write(" ".repeat(i));
                flotEcriture.write("\n");
            }

            for (int i = s.length() / 2; i >= 0; i--) {
                flotEcriture.write(" ".repeat(i));
                flotEcriture.write(s.substring(i, 7 - i));
                flotEcriture.write(" ".repeat(i));
                flotEcriture.write("\n");
            }

            flotEcriture.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
