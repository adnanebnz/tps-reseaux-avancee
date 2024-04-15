package EXO3;

import java.io.*;
import java.net.*;

public class ServerEcho {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2024);
            while (true) {
                Socket sock = server.accept();
                new T(sock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}