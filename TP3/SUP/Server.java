package SUP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws Exception {
        List<Integer> pair = new ArrayList<>();
        List<Integer> impaire = new ArrayList<>();

        DatagramSocket socket = new DatagramSocket(2000);
        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);

            byte[] number = packet.getData();

            ByteArrayInputStream a = new ByteArrayInputStream(number);
            DataInputStream dis = new DataInputStream(a);

            int n = dis.readInt();

            if (n % 2 == 0) {

                pair.add(n);

            } else {
                impaire.add(n);
            }
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream c2 = new ObjectOutputStream(b);

            PairImpaire resFinal = new PairImpaire(pair, impaire);

            System.out.println(resFinal.toString());

            c2.writeObject(resFinal);

            byte[] responseBuffer = b.toByteArray();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length,
                    packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
        }
    }
}