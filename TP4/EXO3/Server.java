import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        while (true) {
            Socket socket = server.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String requete = "";
            double result = 0.0;
            while ((requete = dis.readUTF()) != null) {
                System.out.println(requete);
                StringTokenizer st = new StringTokenizer(requete);
                String operator = st.nextToken();
                operator = operator.toUpperCase();

                int operand1 = Integer.parseInt(st.nextToken());
                int operand2 = Integer.parseInt(st.nextToken());

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
                        if (operand2 == 0) {
                            dos.writeUTF("Result: " + "Division par 0 impossible");
                        }
                        result = (double) operand1 / operand2;
                        break;
                    case "PUIS":
                        result = Math.pow(operand1, operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }

                dos.writeUTF("Result: " + result);

            }
        }
    }
}
