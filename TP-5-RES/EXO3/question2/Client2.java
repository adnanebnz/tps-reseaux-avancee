package EXO3.question2;

import javax.imageio.ImageIO;

import EXO3.ABC;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TP3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(3, 2));

        JLabel typeLabel = new JLabel("Type de données:");
        panel.add(typeLabel);

        String[] fileTypes = { "txt", "image" };
        JComboBox<String> fileTypeBox = new JComboBox<>(fileTypes);
        panel.add(fileTypeBox);

        JLabel pathLabel = new JLabel("Chemin du fichier:");
        panel.add(pathLabel);

        JTextField pathText = new JTextField(20);
        panel.add(pathText);

        JButton sendButton = new JButton("Envoyer");
        panel.add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = (String) fileTypeBox.getSelectedItem();
                String path = pathText.getText();
                sendFile(type, path);
            }
        });
    }

    private static void sendFile(String type, String path) {
        try {
            Socket socket = new Socket("localhost", 9001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(type);
            if (type.equals("image")) {
                BufferedImage bimg = ImageIO.read(new File(path));
                ImageIO.write(bimg, "PNG", socket.getOutputStream());
                System.out.println("Image sent");
            } else if (type.equals("txt")) {
                ABC.mystere(new FileInputStream(path), socket.getOutputStream());
                System.out.println("Text file sent");
            }
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}