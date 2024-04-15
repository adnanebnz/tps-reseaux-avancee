package EXO2;

import java.io.*;
import java.net.*;

public class ClientUDPint {
        public static void main(String[] args) throws Exception {
                int[] tab = { 1, 6, 8, 9, 13, 10 };

                DatagramSocket socket = new DatagramSocket();
                InetAddress serveur = InetAddress.getByName("localhost");
                ByteArrayOutputStream a = new ByteArrayOutputStream();
                ObjectOutputStream b = new ObjectOutputStream(a);
                b.writeObject(tab);
                b.flush();
                byte[] buffer = a.toByteArray();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 2000);
                socket.send(packet);
                System.out.println("Client a envoyé le tableau au serveur");
                socket.close();
        }

}
