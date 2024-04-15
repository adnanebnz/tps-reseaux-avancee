package EXO3;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
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

        String nomClub = b.readUTF().trim();

        List<Joueur> jrs = new ArrayList<>();
        for (Joueur joueur : joueurs) {
            if (joueur.getEquipe().equalsIgnoreCase(nomClub)) {
                jrs.add(joueur);
            }
        }

        oos.writeUTF(nomClub);
        oos.writeObject(jrs);
        byte[] responseBuffer = baos.toByteArray();

        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, packet.getAddress(),
                packet.getPort());
        socket.send(responsePacket);

        socket.close();
    }
}
