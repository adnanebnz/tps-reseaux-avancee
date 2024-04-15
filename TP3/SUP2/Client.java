package SUP2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("localhost", 2024);
            PrintWriter sockOut = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            // get input data from user
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a word: ");
            String word = sc.nextLine();
            sockOut.println(word);
            String recu = sockIn.readLine();
            System.out.println(recu);
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}