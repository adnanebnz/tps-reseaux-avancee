import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Server {
    

    public static void main(String args[]) throws FileNotFoundException {
        HashMap<String, Integer> map = new HashMap<>();
        try (Scanner a = new Scanner(new File("./EXO1/symptomes.txt"))) {
            while (a.hasNextLine()) A.ajouter(map, a.nextLine());  
        }

        System.out.println("Serveur en attente de connexion ...");
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                DataInputStream sockIn = new DataInputStream(sock.getInputStream());
                String symptome;

                while ((symptome = sockIn.readUTF()) != null) {
                    String pattern = ".*" + symptome + ".*"; // This is equivalent to SQL's %LIKE%
                    int total = 0;
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (entry.getKey().matches(pattern)) {
                            total += entry.getValue();
                        }
                    }
                    sockOut.writeInt(total);
                    sockOut.flush();
                }

                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
            }
        }
    }
}