package EXO33;

import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;

import EXO3.ABC;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

                // Read username and password
                String username = dis.readUTF();
                String password = dis.readUTF();

                if (isValidCredentials(username, password)) {
                    dos.writeUTF("Authentication successful");

                    // Handle the file transfer
                    String type = dis.readUTF();
                    if (type.equals("image")) {
                        BufferedImage bimg = ImageIO.read(clientSocket.getInputStream());
                        ImageIO.write(bimg, "PNG", new File("./EXO33/image.png"));
                        System.out.println("Image reçue");
                    } else if (type.equals("txt")) {
                        ABC.mystere(clientSocket.getInputStream(), new FileOutputStream("./EXO33/test2.txt"));
                        System.out.println("Fichier texte reçu");
                    }
                } else {
                    dos.writeUTF("Authentication failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean isValidCredentials(String username, String password) {
            return users.containsKey(username) && users.get(username).equals(password);
        }
    }
}