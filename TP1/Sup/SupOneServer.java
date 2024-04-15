package Sup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class SupOneServer {

  public static int factorial(int n) {
    if (n == 0) {
      return 1;
    } else {
      return n * factorial(n - 1);
    }
  }

  public static void main(String args[]) {

    ServerSocket server = null;
    File fichier = new File("./sup1.txt");
    try {
      server = new ServerSocket(7777);
      FileWriter flotEcriture = new FileWriter(fichier, true);

      while (true) {
        Socket sock = server.accept();
        System.out.println("connecte");
        ObjectOutputStream sockOut = new ObjectOutputStream(sock.getOutputStream());
        ObjectInputStream sockIn = new ObjectInputStream(sock.getInputStream());

        // Read the list of integers
        List<Integer> numbers = (List<Integer>) sockIn.readObject();
        System.out.println("Received numbers: " + numbers);

        // Calculate factorials and send them back
        for (int n : numbers) {
          int facto = factorial(n);
          flotEcriture.write("Factorial of " + n + " is " + facto + "\n");
          sockOut.writeObject(facto);
        }
        flotEcriture.close();

        System.out.println("Taille de la zone tampon : " + sock.getReceiveBufferSize());
        System.out.println("Taille de la zone tampon : " + sock.getSendBufferSize());

        sockOut.close();
        sock.close();
      }
    } catch (IOException | ClassNotFoundException e) {
      try {
        server.close();
      } catch (IOException e2) {
      }
    }
  }// fin main

}