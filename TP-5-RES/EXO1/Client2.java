import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client2 extends JFrame implements ActionListener {
    Socket sock = null;
    DataOutputStream sockOut = null;
    DataInputStream sockIn = null;
    private JButton jbtGetMoy = new JButton("Afficher les symptomes");
    private JTextField jtfPatient = new JTextField();
    private JTextField jtfSymptomes = new JTextField();

    public Client2() {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(2, 2));
        panneau.add(new JLabel("Patient"));
        panneau.add(jtfPatient);
        panneau.add(new JLabel("Symptomes"));
        panneau.add(jtfSymptomes);
        add(panneau, BorderLayout.CENTER);
        add(jbtGetMoy, BorderLayout.SOUTH);
        jbtGetMoy.addActionListener(this);
    }

    public void init() {
        try {
            sock = new Socket("localhost", 7777);
            sockOut = new DataOutputStream(sock.getOutputStream());
            sockIn = new DataInputStream(sock.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : localhost");
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String symptomes = "";
        try {
            sockOut.writeUTF(jtfPatient.getText().trim());
            sockOut.flush();
        } catch (IOException ex) {
        }
        try {
            symptomes = sockIn.readUTF();
        } catch (IOException ex) {
        }
            jtfSymptomes.setText(symptomes);
    }

    public static void main(String[] args) {
        Client2 a = new Client2();
        a.setTitle("EXO1-3");
        a.setSize(350, 150);
        a.init();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }
}