package SUP1;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Sup {

  public static File concatFiles(File file1, File file2, File fileOutput) {
    try {
      FileWriter fw = new FileWriter(fileOutput);
      Scanner a = new Scanner(file1);
      Scanner b = new Scanner(file2);
      while (a.hasNextLine() || b.hasNextLine()) {
        if (b.hasNextLine()) {
          fw.write(b.nextLine());
          fw.write("\n");
        }
        if (a.hasNextLine()) {
          fw.write(a.nextLine());
          fw.write("\n");
        }

      }

      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    File fichier = new File("./output.txt");
    return fichier;
  }

  public static void main(String[] args) {
    File fileA = new File("./a.txt");
    File fileB = new File("./b.txt");
    File fileOutput = new File("./output.txt");
    concatFiles(fileA, fileB, fileOutput);
  }
}