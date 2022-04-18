import service.client.Client;

import java.io.*;
import java.net.Socket;

public class ClientTest {
    final static String IP = "127.0.0.1";
    final static int PORT = 9090;

    public static void main(String[] args) {
        try {
            // 1. 소켓 생성과 동시에 서버와 연결
            Socket clientSocket = new Socket(IP, PORT);

            System.out.println("[Connected] Server Address : " + IP);

            // 2. 키보드 입력 받을 스트림 생성a
            InputStreamReader inK = new InputStreamReader(System.in);
            BufferedReader keyboard = new BufferedReader(inK);

            // 3. 서버와 연결된 양방향 스트림 얻기
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

            // 5. quit 했으면 스트림 모두 종료
            keyboard.close();
            pw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
