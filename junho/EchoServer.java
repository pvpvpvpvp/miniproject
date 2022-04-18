package junho;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//1. 클라이언트가 서버에 파일을 요청한다(파일 이름 전송)
//2. 서버는 해당 파일을 연결한 후 클라이언트에 전송한다.
//3. 전송이 끝나면 통신을 해제한다.
public class EchoServer {
    public static void main(String[] args) throws IOException {
        // 1) 소켓 생성(휴대폰 구매)
        //    ip/port가 필요한데
        //    ip는 현재 Host의 ip를 자동할당
        //    대신 port는 정해줘야 한다(9000)
        ServerSocket serverSocket = new ServerSocket(9000);

        // 2) 클라이언트의 접속을 기다린다
        // accpet()를 호출하면 대기하고 있다가 클라이언트가 접속연결되면
        // accetp()는 클라이언트와 연결된 새로운 연결소켓을 반환한다
        System.out.println("Wait client...");
        Socket conSocket = serverSocket.accept();

        // 2-1) 상대방의 연결정보 확인
        InetAddress inetAddr = conSocket.getInetAddress();
        System.out.println(inetAddr.getHostAddress() + " connect!");

        // 3) 연결소켓으로 통신을 할 수 있다
        // 전송 스트림
        OutputStream out = conSocket.getOutputStream();
        OutputStreamWriter outW = new OutputStreamWriter(out);
        PrintWriter pw = new PrintWriter(outW);

        // 수신 스트림
        InputStream in = conSocket.getInputStream();
        InputStreamReader inR = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inR);

        //파일 넣기
        OutputStream outS = new FileOutputStream("member.txt",true);
        BufferedOutputStream bOut = new BufferedOutputStream(outS);
        PrintWriter pw01 = new PrintWriter(bOut);

        //파일 읽기
        InputStream inFile = new FileInputStream("member.txt");
        InputStreamReader inRF = new InputStreamReader(inFile);
        BufferedReader brF = new BufferedReader(inRF);


        String str;
        while ((str=brF.readLine())!=null){
            System.out.println(str);
        }

        // 4) 송수신
        while(true) {
            // 클라이언트가 보내는 데이터를 기다리다가 도착하면 문자열 반환
            String line = br.readLine();
            // 만약 데이터를 기다리다가 null이 반환되었다면 연결이 끊어진 것임
            if(line == null) {
                System.out.println("Client Disconnect!");
                break;
            }
            System.out.println("Received : " + line);

            pw.println(line);	// 클라이언트에 그래도 돌려줌
            pw.flush();			// 버퍼에 저장된 데이터를 즉시 전송해라

            pw01.println(line);
            pw01.flush();
        }


        // 5) 스트림 종료
        pw.close();
        br.close();
        System.out.println("client - server Ended!");
    }

}
