import java.io.*;
import java.net.Socket;

public class Client {
    private static final int PORT = 9000;
    private static final String IP = "127.0.0.1";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP,PORT);

            System.out.println("[Connected] Server Address : " + IP);

            InputStream in = socket.getInputStream();
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inR);

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
