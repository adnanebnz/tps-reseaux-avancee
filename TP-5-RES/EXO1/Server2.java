import java.net.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Server2 {

    public static void main(String args[]) throws FileNotFoundException {
        Map<String, List<String>> map = new HashMap<>();
        try (Scanner a = new Scanner(new File("./EXO1/symptomesnom.txt"))) {
            while (a.hasNextLine()) {
                String line = a.nextLine();
                String[] parts = line.split(" ", 2);
                String name = parts[0];
                String symptom = parts[1];
                map.computeIfAbsent(name, k -> new ArrayList<>()).add(symptom);
            }
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
                    List<String> symptoms = map.get(patient);
                    if (symptoms != null) {
                        String allSymptoms = symptoms.stream().collect(Collectors.joining(", "));
                        sockOut.writeUTF(allSymptoms);
                    }
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