package EXO3;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du club: ");
        String nomClub = scanner.nextLine().trim();
        scanner.close();

        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ObjectOutputStream b = new ObjectOutputStream(a);
        b.writeUTF(nomClub);
        b.flush();
        byte[] buffer = a.toByteArray();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 2000);
        socket.send(packet);

        byte[] responseBuffer = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
        socket.receive(responsePacket);

        ByteArrayInputStream bais = new ByteArrayInputStream(responsePacket.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        String receivedClubName = ois.readUTF();
        @SuppressWarnings("unchecked")
        List<Joueur> receivedPlayers = (List<Joueur>) ois.readObject();

        System.out.println("Club: " + receivedClubName);
        System.out.println("Joueurs:");
        System.out.println(receivedPlayers.toString());
        socket.close();
    }
}
