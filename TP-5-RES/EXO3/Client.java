package EXO3;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("localhost", 9001);
        ABC.mystere(new FileInputStream("./EXO3/test1.txt"), sock.getOutputStream());
        sock.close();
    }
}
