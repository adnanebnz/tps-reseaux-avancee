package EXO1;

import java.io.*;
import java.net.*;

public class Recepteur {
    @SuppressWarnings("deprecation")
    public static void main(String argv[]) throws IOException, ClassNotFoundException {
        InetAddress group = InetAddress.getByName("235.1.1.1");
        MulticastSocket socket = new MulticastSocket(4000);
        socket.joinGroup(group);
        byte[] buf = new byte[1024];
        DatagramPacket recv = new DatagramPacket(buf, buf.length);
        System.out.println("En attente de reception ...");
        socket.receive(recv);
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Entreprise entreprise = (Entreprise) ois.readObject();
        System.out.println("Debut reception\n" + entreprise + "\nFin reception");
        socket.close();

    }
}
