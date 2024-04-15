package EXO3;

import java.io.*;
import java.net.*;

public class ClientEcho {
    public static void main(String[] args) throws IOException {
        Socket sock = null;
        OutputStream sockOut = null;
        InputStream sockIn = null;
        try {
            int port = 2024;
            sock = new Socket("localhost", port);
            sockOut = sock.getOutputStream();
            sockIn = sock.getInputStream();
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : localhost");
            System.exit(1);
        }
        byte[] buffer1 = new byte[1024];
        buffer1 = "ABCD".getBytes();
        try {
            sockOut.write(buffer1);
            sockOut.flush();
        } catch (IOException e) {
        }
        byte[] buffer2 = new byte[1024];
        int lu = sockIn.read(buffer2);
        System.out.println("Mot envoyé par le serveur est : " + new String(buffer2));
        System.out.println("Nombre d'octets lu : " + lu);
        sockOut.close();
        sockIn.close();
        sock.close();
    }
}
