import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {
    Socket sock = null;
    DataOutputStream sockOut = null;
    DataInputStream sockIn = null;
    private JButton jbtGetMoy = new JButton("Afficher le nombre de fois");
    private JTextField jtfSymptome = new JTextField();
    private JTextField jtfNbrFois = new JTextField();

    public Client() {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(2, 2));
        panneau.add(new JLabel("Symptome"));
        panneau.add(jtfSymptome);
        panneau.add(new JLabel("Nombre de fois"));
        panneau.add(jtfNbrFois);
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
        int nombreDeFois = 0;
        try {
            sockOut.writeUTF(jtfSymptome.getText().trim());
            sockOut.flush();
        } catch (IOException ex) {
        }
        try {
            nombreDeFois = sockIn.readInt();
        } catch (IOException ex) {
        }
            jtfNbrFois.setText(String.valueOf(nombreDeFois));
    }

    public static void main(String[] args) {
        Client a = new Client();
        a.setTitle("EXO1");
        a.setSize(350, 150);
        a.init();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }
}