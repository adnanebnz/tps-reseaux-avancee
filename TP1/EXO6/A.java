package EXO6;

import java.io.*;

public class A {
    public static void main(String[] args) {
        File fichier = new File("./EXO6/test3.txt");
        try {
            FileWriter flotEcriture = new FileWriter(fichier);
            for (int i = 1; i <= 7; i++) {
                flotEcriture.write("mirsdgl", 0, 8 - i);
                // 2éme parametre pour indiquer le début de la chaine et le 3éme parametre pour
                // indiquer la fin de la chaine
                flotEcriture.write("\n");
            }
            flotEcriture.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
