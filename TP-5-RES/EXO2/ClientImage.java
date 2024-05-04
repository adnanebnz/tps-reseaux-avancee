package EXO2;

import java.io.*;
import java.awt.image.BufferedImage;
import java.net.Socket;
import javax.imageio.ImageIO;

public class ClientImage {
    static BufferedImage bimg;

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 6066);
            bimg = ImageIO.read(new File("./EXO2/algeria_eats.PNG"));
            ImageIO.write(bimg, "PNG", client.getOutputStream());
            client.close();
        } catch (IOException e) {
        }
    }
}
