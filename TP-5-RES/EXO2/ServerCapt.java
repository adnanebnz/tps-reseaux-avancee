package EXO2;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ServerCapt {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6000);
        try {
            Socket server = serverSocket.accept();
            BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JLabel(new ImageIcon(img)));
            frame.pack();
            frame.setVisible(true);

            File outputfile = new File("./EXO2/saved.png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException | HeadlessException e) {
        }
    }
}