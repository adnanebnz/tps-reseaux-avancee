import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ServerEtudiant {
    public static void main(String args[]) {
        // QUESTION 4
        Etudiant[] tabEtudiant = {
                new Etudiant("A", "MID", 9),
                new Etudiant("B", "MID", 5),
                new Etudiant("C", "MID", 14),
                new Etudiant("D", "RSD", 10),
                new Etudiant("E", "RSD", 12),
                new Etudiant("F", "RSD", 16),
                new Etudiant("G", "SIC", 11),
                new Etudiant("H", "SIC", 10),
                new Etudiant("I", "SIC", 16),
                new Etudiant("J", "GL", 13),
                new Etudiant("K", "GL", 12),
                new Etudiant("L", "GL", 17)
        };
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            Socket sock = null;
            while (true) {
                sock = server.accept();
                System.out.println("connecte");
                ObjectOutputStream sockOut = new ObjectOutputStream(sock.getOutputStream());
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String recu;
                while ((recu = sockIn.readLine()) != null) {
                    System.out.println("recu :" + recu);
                    if (recu.matches("\\d+")) {
                        int moy = Integer.parseInt(recu);
                        List<Etudiant> etudiants = new ArrayList<>();
                        for (Etudiant etudiant : tabEtudiant) {
                            if (etudiant.moy > moy) {
                                etudiants.add(etudiant);
                            }
                        }
                        sockOut.writeObject(etudiants);
                    } else if (recu.matches("[A-Z]+")) {
                        String specialte = recu;
                        Etudiant bestStudent = null;
                        for (Etudiant etudiant : tabEtudiant) {
                            if (etudiant.specialite.equals(specialte)
                                    && (bestStudent == null || etudiant.moy > bestStudent.moy)) {
                                bestStudent = etudiant;
                            }
                        }
                        sockOut.writeObject(bestStudent);
                    } else {
                        String nom = recu.trim();
                        for (Etudiant etudiant : tabEtudiant) {
                            if (etudiant.getNom().equals(nom)) {
                                sockOut.writeObject(etudiant);
                                break;
                            }
                        }
                    }
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

        try {
            // get the data sent from client
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
