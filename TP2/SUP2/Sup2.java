package SUP2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sup2 {
  static Integer var = 1;
  static ArrayList<Symptom> list = new ArrayList<>();

  public static void main(String args[]) throws IOException {
    try (Scanner a = new Scanner(new File("./EXO4/symptomes.txt"))) {
      while (a.hasNextLine()) {
        Symptom s = new Symptom();
        s.nom = a.nextLine();
        for (Symptom symptom : list) {
          if (symptom.nom.equals(s.nom)) {
            s.occurences = s.occurences + 1;
          }
        }
        list.add(s);
      }

      list.sort((Symptom aa, Symptom b) -> b.occurences - aa.occurences);

      for (Symptom symptom : list) {
        System.out.println("Symptom : " + symptom.nom + " Occ: " + symptom.occurences);
      }
    }
    File outputFile = new File("./EXO4/outputFile.txt");
    FileWriter fw = new FileWriter(outputFile);
    for (Symptom symptom : list) {
      fw.write(symptom.nom + " Occ: " + symptom.occurences);
      fw.write("\n");
    }
    fw.close();
  }
}
