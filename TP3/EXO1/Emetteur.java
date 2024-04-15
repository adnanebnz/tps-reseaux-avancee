package EXO1;

import java.io.*;
import java.net.*;

public class Emetteur {
    public static void main(String argv[]) throws IOException {
        Entreprise entreprise = new Entreprise(10, "SOGERHWIT");
        InetAddress group = InetAddress.getByName("235.1.1.1");
        DatagramSocket socket = new DatagramSocket();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(entreprise);
        byte[] buffer = baos.toByteArray();
        DatagramPacket hi = new DatagramPacket(buffer, buffer.length, group, 4000);
        socket.send(hi);
        System.out.println("Fin emission");

        socket.close();
    }
}
