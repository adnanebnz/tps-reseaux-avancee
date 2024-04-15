package SUP;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        File fichier = new File("./sup1.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        while (scanner.hasNextLine()) {
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            DataOutputStream b = new DataOutputStream(a);
            try {
                int number = scanner.nextInt();
                if (number == -1) {
                    break;
                } else {
                    b.writeInt(number);
                    b.flush();
                    byte[] buffer = a.toByteArray();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 2000);
                    socket.send(packet);
                    byte[] responseBuffer = new byte[1024];
                    DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                    socket.receive(responsePacket);
                    byte[] res = responsePacket.getData();
                    ByteArrayInputStream bais = new ByteArrayInputStream(res);
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    PairImpaire finalRes = (PairImpaire) ois.readObject();
                    System.out.println("This result is : " + finalRes);

                    try (FileWriter flotEcriture = new FileWriter(fichier)) {
                        flotEcriture.write(finalRes.toString());
                    }

                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (or '-1' to finish): ");
                scanner.next();
            }
        }
        scanner.close();
    }
}
