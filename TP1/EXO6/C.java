package EXO6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class C {
    public static void main(String[] args) {
        File fichier = new File("./EXO6/test3.txt");
        try {
            FileWriter flotEcriture = new FileWriter(fichier);
            String s = "mirsdgl";
            // at first we are deleting irsdg and replacing them with spaces
            // THEN were adding one character from the left and one from the right
            // we are deleting from i+1 and length-1
            // m l
            // mi gl
            // mir dgl
            // mirsdgl
            // mir dgl
            // mi gl
            // m l
            // for (int i = 0; i < s.length() / 2; i++) {
            // if (i != s.length() / 2) {
            // flotEcriture.write(s, 0, i + 1);
            // } else {
            // flotEcriture.write(s, 0, i);
            // }
            // for (int j = 0; i <= s.length() - 2 * i - 2; i++) {
            // flotEcriture.write(" ");
            // }

            flotEcriture.close();
        } catch (

        IOException e) {
            e.printStackTrace();
        }
    }
}
