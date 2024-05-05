package EXO33;

import javax.imageio.ImageIO;
import javax.swing.*;

import EXO3.ABC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientFinal {
    private static JFrame frame;
    private static JPanel authPanel;
    private static JPanel filePanel;

    public static void main(String[] args) {
        frame = new JFrame("TP3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 200);
        frame.setLocationRelativeTo(null);

        authPanel = createAuthPanel();
        filePanel = createFilePanel();

        frame.add(authPanel);

        frame.setVisible(true);
    }

    private static JPanel createAuthPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                // log user and password to console
                System.out.println("User: " + user + ", Password: " + password);
                if (authenticate(user, password)) {
                    frame.remove(authPanel);
                    frame.add(filePanel);
                    frame.validate();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    private static JPanel createFilePanel() {
        System.out.println("createFilePanel");
        JPanel panel = new JPanel(new GridLayout(5, 2));

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

        return panel;
    }

    private static Socket socket;

    private static boolean authenticate(String user, String password) {
        try {
            socket = new Socket("localhost", 9001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF(user);
            out.writeUTF(password);
            System.out.println("USER AND PASSWORD SENT");

            String response = in.readUTF();
            if ("Authentication failed".equals(response)) {
                System.out.println("Invalid credentials");
                return false;
            } else {
                System.out.println("Valid credentials");
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void sendFile(String type, String path) {
        try {
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