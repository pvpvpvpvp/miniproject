import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
    final static String IP = "127.0.0.1";
    final static int PORT = 9000;

    public static void main(String[] args) {
        try {
            // 1. 소켓 생성과 동시에 서버와 연결
            Socket clientSocket = new Socket(IP, PORT);

            // 2. 키보드 입력 받을 스트림 생성
            InputStreamReader inK = new InputStreamReader(System.in);
            BufferedReader keyboard = new BufferedReader(inK);

            // 3. 서버와 연결된 양방향 스트림 얻기
            OutputStream out = clientSocket.getOutputStream();
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);

            InputStream in = clientSocket.getInputStream();
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inR);

            // 사용자로부터 데이터 입력
            //  -> 서버로 전송
            //  -> 서버로부터 echo 수신
            //  quit 입력 전 까지 계속 반복
            StringBuilder sb = new StringBuilder();
            while (true) {
                System.out.print("input >> ");

                sb.setLength(0);
                sb.append(keyboard.readLine());

                if (sb.toString().equals("quit")) {
                    System.out.println("Client Quit!");
                    break;
                }
                System.out.println("Send Server : " + sb.toString());
                pw.println(sb.toString());
                pw.flush();

                String echo = br.readLine();
                System.out.println("Received From Server : " + echo);
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
