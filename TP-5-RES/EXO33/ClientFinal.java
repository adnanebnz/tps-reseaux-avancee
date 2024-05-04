package EXO33;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFinal {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TP3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("User:");
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
                login(user, password);
            }
        });
    }

    private static void login(String user, String password) {
        try {
            Socket socket = new Socket("localhost", 9001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(user);
            out.writeUTF(password);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String response = in.readUTF();
            JOptionPane.showMessageDialog(null, response);
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}