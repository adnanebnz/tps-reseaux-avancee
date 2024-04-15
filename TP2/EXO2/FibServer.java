package EXO2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class FibServer {
    public static void main(String args[]) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("Connected");
                DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                DataInputStream sockIn = new DataInputStream(sock.getInputStream());
                int n = sockIn.readInt();
                System.out.println("Received: " + n);
                int[] fib = new int[n];
                fib[0] = 0;
                if (n > 1) {
                    fib[1] = 1;
                    for (int i = 2; i < n; i++) {
                        fib[i] = fib[i - 1] + fib[i - 2];
                    }
                }
                for (int i = 0; i < n; i++) {
                    sockOut.writeInt(fib[i]);
                }
                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}