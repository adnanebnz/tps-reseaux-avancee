package EXO2;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDPintv2 {
    public static void main(String[] args) throws Exception {
        int[] tab = { 1, 6, 8, 9, 13, 10 };

        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");

        for (int i : tab) {
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            DataOutputStream b = new DataOutputStream(a);
            b.writeInt(i);
            b.flush();
            byte[] buffer = a.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 2000);
            socket.send(packet);
            System.out.println("Client a envoyé l'entier " + i + " au serveur\n");
        }

        socket.close();
    }
}
