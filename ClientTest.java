import client.Client;

import java.io.*;
import java.net.Socket;

public class ClientTest{

    public static void main(String[] args) {
        new Thread(new ClientThread()).start();
    }

}

class ClientThread implements Runnable {
    private final static String IP = "127.0.0.1";
    private final static int PORT = 9010;

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(IP, PORT);

            System.out.println("[Connected] Server Address : " + IP);

            InputStreamReader inK = new InputStreamReader(System.in);
            BufferedReader keyboard = new BufferedReader(inK);

            OutputStream out = clientSocket.getOutputStream();
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);

            InputStream in = clientSocket.getInputStream();
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inR);

            StringBuilder sb = new StringBuilder();
            Client client = new Client();

            while (Client.flag) {
                client.showMenu();

                if(Client.flag){
                    sb.setLength(0);
                    sb.append(keyboard.readLine());

                    pw.println(sb.toString());
                    pw.flush();
                }
            }

            keyboard.close();
            pw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

