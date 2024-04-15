package EXO3;

import java.io.*;
import java.net.*;

public class T extends Thread {
    private Socket socket;

    public T(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            OutputStream sockOut = socket.getOutputStream();
            InputStream sockIn = socket.getInputStream();
            byte[] buffer1 = new byte[1024];
            int lu = sockIn.read(buffer1);
            System.out.println("Mot envoyé par le client est : " + new String(buffer1, 0, lu));
            System.out.println("Nombre d'octets lu : " + lu);
            byte[] buffer2 = "BONJOUR TOUT LE MONDE".getBytes();
            sockOut.write(buffer2);
            sockOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}