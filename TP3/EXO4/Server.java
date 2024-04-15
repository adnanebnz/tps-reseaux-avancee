package EXO4;

import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(2000);

        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);

            String operation = new String(packet.getData()).trim();
            StringTokenizer st = new StringTokenizer(operation);
            String operator = st.nextToken();
            int operand1 = Integer.parseInt(st.nextToken());
            int operand2 = Integer.parseInt(st.nextToken());

            double result = 0;
            switch (operator) {
                case "ADD":
                    result = operand1 + operand2;
                    break;
                case "MUL":
                    result = operand1 * operand2;
                    break;
                case "SOUS":
                    result = operand1 - operand2;
                    break;
                case "DIV":
                    result = (double) operand1 / operand2;
                    break;
                case "PUIS":
                    result = Math.pow(operand1, operand2);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }

            byte[] responseBuffer = String.valueOf(result).getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length,
                    packet.getAddress(), packet.getPort());
            socket.send(responsePacket);

            socket.close();
        }
    }
}