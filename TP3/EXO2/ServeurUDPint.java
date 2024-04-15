package EXO2;

import java.io.*;
import java.net.*;

public class ServeurUDPint {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(2000);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        socket.receive(packet);
        byte[] data = packet.getData();
        ByteArrayInputStream a = new ByteArrayInputStream(data);
        ObjectInputStream b = new ObjectInputStream(a);

        int[] tab = (int[]) b.readObject();

        for (int i : tab) {
            System.out.println("Entier recu : " + i);
        }
        socket.close();

    }
}
