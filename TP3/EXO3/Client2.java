package EXO3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez un character: ");
        char character = scanner.nextLine().charAt(0);
        scanner.close();

        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ObjectOutputStream b = new ObjectOutputStream(a);
        b.writeChar(character);
        b.flush();
        byte[] buffer = a.toByteArray();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 2000);
        socket.send(packet);

        byte[] responseBuffer = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
        socket.receive(responsePacket);

        ByteArrayInputStream bais = new ByteArrayInputStream(responsePacket.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        @SuppressWarnings("unchecked")
        List<Joueur> receivedPlayers = (List<Joueur>) ois.readObject();
        ;
        System.out.println("Joueurs:");
        System.out.println(receivedPlayers.toString());
        socket.close();
    }
}
