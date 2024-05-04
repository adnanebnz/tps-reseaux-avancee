package EXO3;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) throws IOException {
        Socket sock = new ServerSocket(9001).accept();
        ABC.mystere(sock.getInputStream(), new FileOutputStream("./EXO3/test2.txt"));
        sock.close();
        // SA COPIER LE CONTENU DU FICHIER test1.txt DANS LE FICHIER test2.txt
    }
}
