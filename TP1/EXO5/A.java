package EXO5;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("./EXO5/test1.txt");
        File outputFile = new File("./EXO5/test2.txt");

        FileReader in = new FileReader(inputFile);
        FileWriter out = new FileWriter(outputFile, true);
        // added append to add the word to the end of the file
        int c;
        while ((c = in.read()) != -1)
            out.write(c);
        in.close();
        out.close();
    }
}
