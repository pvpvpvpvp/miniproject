package socketserver;

import java.io.*;
import java.net.Socket;

public class EchoThread extends Thread{

    private Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        try {
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);

            InputStream in = socket.getInputStream();
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inR);

            StringBuilder sb = new StringBuilder();
            String line;
            while (true) {
                line = br.readLine();

                sb.setLength(0);
                sb.append("[" + socket.getInetAddress().getHostAddress() + "] ");
                sb.append(line);

                if (line == null) {
                    System.out.println("[Disconnected] Client Address : " + socket.getInetAddress().getHostAddress());
                    break;
                }
                System.out.println(sb.toString());
                pw.println(sb.toString());
                pw.flush();
            }

            br.close();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
