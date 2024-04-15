package EXO3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Server2 {
    public static void main(String[] args) throws Exception {
        Joueur j1 = new Joueur(9, "BENZEMA", "REAL");
        Joueur j2 = new Joueur(10, "MODRIC", "REAL");
        Joueur j3 = new Joueur(26, "MAHREZ", "City");
        Joueur j4 = new Joueur(30, "MESSI", "PSG");
        Joueur j5 = new Joueur(10, "NEYMAR", "PSG");

        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(j1);
        joueurs.add(j2);
        joueurs.add(j3);
        joueurs.add(j4);
        joueurs.add(j5);

        DatagramSocket socket = new DatagramSocket(2000);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        socket.receive(packet);

        ByteArrayInputStream a = new ByteArrayInputStream(packet.getData());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectInputStream b = new ObjectInputStream(a);
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        char character = b.readChar();

        List<Joueur> jrs = new ArrayList<>();
        for (Joueur joueur : joueurs) {
            if (joueur.getNom().charAt(0) == character) {
                jrs.add(joueur);
            }
        }

        oos.writeObject(jrs);
        byte[] responseBuffer = baos.toByteArray();

        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, packet.getAddress(),
                packet.getPort());
        socket.send(responsePacket);

        socket.close();
    }
}
