import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

class Server2 {
    

    public static void main(String args[]) throws FileNotFoundException {
        HashMap<String, Integer> map = new HashMap<>();
        try (Scanner a = new Scanner(new File("./EXO1/symptomesnom.txt"))) {
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
                String patient;

                while ((patient = sockIn.readUTF()) != null) {
                    StringTokenizer st = new StringTokenizer(patient, " ");
                    String symptomes = "";
                    while (st.hasMoreTokens()) {
                        String symptome = st.nextToken();
                        String pattern = ".*" + symptome + ".*"; 
                        for (Map.Entry<String, Integer> entry : map.entrySet()) {
                            if (entry.getKey().matches(pattern)) {
                                symptomes += entry.getKey() + " ";
                            }
                        }
                    }
                    
                    sockOut.writeUTF(symptomes);
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