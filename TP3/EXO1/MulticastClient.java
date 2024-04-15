package EXO1;

import java.io.*;
import java.net.*;

public class MulticastClient {
    public static void main(String[] args) throws Exception {
        Entreprise entreprise = new Entreprise(10, "SOGERHWIT");

        MulticastSocket socket = new MulticastSocket();
        InetAddress group = InetAddress.getByName("224.0.0.1"); // Adresse IP de groupe multicast
        int port = 9000; // Port pour le groupe multicast

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(entreprise);
        oos.flush();

        byte[] buffer = baos.toByteArray();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
        socket.send(packet);
        System.out.println("Objet envoyé au groupe multicast");
        socket.close();
    }
}
