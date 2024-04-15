package Sup;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupOneClient {

  public static void main(String args[]) {
    try {
      Socket sock = new Socket("localhost", 7777);
      DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
      DataInputStream sockIn = new DataInputStream(sock.getInputStream());

      Scanner sc = new Scanner(System.in);
      List<Integer> list = new ArrayList<>();
      String input;
      while (sc.hasNext() && !(input = sc.next()).equals("exit")) {
        try {
          int number = Integer.parseInt(input);
          if (number > 0) {
            list.add(number);
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter an integer or 'exit' to quit.");
        }
      }

      sockOut.writeInt(list.size());

      for (Integer number : list) {
        sockOut.writeInt(number);
        // System.out.println(sockIn.readInt());
      }

      sc.close();
      sockOut.close();
      sockIn.close();
      sock.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}