import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class ClientEtudiant {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        Socket sock = new Socket();
        SocketAddress localaddr = new InetSocketAddress(8888);
        SocketAddress serveur = new InetSocketAddress("localhost", 7777);
        sock.bind(localaddr);
        sock.connect(serveur);
        PrintWriter sockOut = null;
        ObjectInputStream sockIn = null;
        try {
            // ? tester une porte locale | la porte local est automatiquement attribuée par
            // ? le OS
            // sock = new Socket("localhost", 7777, InetAddress.getByName("localhost"),
            // 5000);
            // QUESTION 3
            // sock = new Socket(hostName, 8888);

            sockOut = new PrintWriter(sock.getOutputStream(), true);
            sockIn = new ObjectInputStream(sock.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : " + hostName);
            System.exit(1);
        }
        try {
            // QUESTION 4 - 1
            // int rand = (int) (Math.random() * 19 + 1);

            // sockOut.println(rand);

            // QUESTION 4 - 2
            sockOut.println("RSD");

            Object recu = sockIn.readObject(); // récupérer l’objet Etudiant envoyé par le serveur
            // QUESTION 1
            int inBufferSize = sock.getSendBufferSize();
            int outBufferSize = sock.getReceiveBufferSize();
            // QUESTION 2
            int localPort = sock.getLocalPort();
            int distantPort = sock.getPort();
            System.out.println("Local port: " + localPort);
            System.out.println("Distant port: " + distantPort);
            if (recu == null)
                System.out.println("erreur de connection");
            else {
                System.out.println("IN BUFFER SIZE " + inBufferSize);
                System.out.println("OUT BUFFER SIZE " + outBufferSize);
                // System.out.println("serveur -> client : " + etudiant);

                // QUESTION 4 - 1

                // List<Etudiant> etudiant = (List<Etudiant>) recu;
                // for (Etudiant e : etudiant) {
                // System.out.println(e.toString());
                // }

                // QUESTION 4 - 2

                Etudiant etudiant = (Etudiant) recu;
                System.out.println("serveur -> client : " + etudiant);

            }
        } catch (ClassNotFoundException e) {
            System.err.println("Classe inconnue : " + hostName);
            System.exit(1);
        }

        sockOut.close();
        sockIn.close();
        sock.close();
    }
}
