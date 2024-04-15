package EXO3;

import java.io.*;
import java.net.*;

public class RSD {
    public static void main(String[] args) throws IOException {
        String host = "www.facebook.com"; // ou n’importe quelle autre machine (localhost en local par ex.)
        InetAddress ip = InetAddress.getByName(host);
        System.out.println(ip);
        int debut = (int) System.currentTimeMillis();
        boolean var = ip.isReachable(5000);
        if (var)
            System.out.println("OK dans " + ((int) System.currentTimeMillis() - debut) + " ms");
        else
            System.out.println("Not OK");
    }
}
