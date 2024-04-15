package EXO1;

import java.io.*;
import java.net.*;

public class MulticastServer {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        InetAddress group = InetAddress.getByName("224.0.0.1"); // Adresse IP de groupe multicast
        int port = 9000; // Port pour le groupe multicast

        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(group);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Entreprise entreprise = (Entreprise) ois.readObject();
        System.out.println("Objet reçu du groupe multicast : " + entreprise);

        socket.leaveGroup(group);
        socket.close();
    }
}
