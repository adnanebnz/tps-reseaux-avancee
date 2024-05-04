package EXO33;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server {
    private static final HashMap<String, String> users = new HashMap<>();

    static {
        users.put("user1", "password1");
        users.put("user2", "password2");
        users.put("user3", "password3");
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            Socket socket = serverSocket.accept();
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                // Authenticate the client
                String username = in.readUTF();
                String password = in.readUTF();
                if (!password.equals(users.get(username))) {
                    out.writeUTF("Invalid login/password.");
                    socket.close();
                    return;
                }
                out.writeUTF("Login successful.");

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}