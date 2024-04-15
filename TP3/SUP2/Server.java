package SUP2;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2024);
            while (true) {
                Socket sock = server.accept();
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter sockOut = new PrintWriter(sock.getOutputStream(), true);
                String recu = sockIn.readLine();
                String[] words = recu.split(" ");
                StringBuilder response = new StringBuilder();
                for (String word : words) {
                    response.append(word.charAt(word.length() - 1));
                }
                sockOut.println(response.toString());
                sock.close();
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}