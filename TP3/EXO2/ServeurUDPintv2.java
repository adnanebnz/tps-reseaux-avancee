package EXO2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurUDPintv2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(2000);

        for (int i = 0; i < 6; i++) {
            DatagramPacket packet = new DatagramPacket(new byte[4], 4);
            socket.receive(packet);
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            DataInputStream dis = new DataInputStream(bais);
            int entier = dis.readInt();
            System.out.println("Entier recu : " + entier);
        }

        socket.close();

    }

}
