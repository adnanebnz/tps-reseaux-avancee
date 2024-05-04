package EXO3.question2;

import javax.imageio.ImageIO;

import EXO3.ABC;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            Socket clientSocket = serverSocket.accept();

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            String type = in.readUTF();
            if (type.equals("image")) {
                BufferedImage bimg = ImageIO.read(clientSocket.getInputStream());
                ImageIO.write(bimg, "PNG", new File("./EXO3/question2/image.png"));
                System.out.println("Image reçue");
            } else if (type.equals("txt")) {
                ABC.mystere(clientSocket.getInputStream(), new FileOutputStream("./EXO3/question2/test2.txt"));
                System.out.println("Fichier texte reçu");
            }
            clientSocket.close();
        }
    }
}
