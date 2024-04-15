package EXO2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class FibClient {
    public static void main(String args[]) {
        try {
            Socket sock = new Socket("localhost", 7777);
            DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
            DataInputStream sockIn = new DataInputStream(sock.getInputStream());
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            sockOut.writeInt(n);
            System.out.println("Fibonacci numbers are:");
            for (int i = 0; i < n; i++) {
                System.out.print(sockIn.readInt() + " ");
            }
            System.out.println();
            sockOut.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}