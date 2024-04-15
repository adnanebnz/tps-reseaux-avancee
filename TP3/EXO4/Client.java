package EXO4;

import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the operation: ");
        String operation = scanner.nextLine();
        scanner.close();

        byte[] buffer = operation.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 2000);
        socket.send(packet);

        byte[] responseBuffer = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
        socket.receive(responsePacket);

        String response = new String(responsePacket.getData()).trim();
        System.out.println("The result is: " + response);

        socket.close();
    }
}