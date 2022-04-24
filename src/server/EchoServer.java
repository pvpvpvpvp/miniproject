package server;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
서버는 서비스를 제공하는 프로세스를 일컫는다.

TCP > UDP
1) 접속할 전화기가 필요(소켓 생성)
2) 고정된 주소를 가진다(bind - 전화기에 전화번호 부여)
3) 접속 요청하는 클라이언트를 받아줘야 한다(accept)
4) 요청을 받아들이면 송수신 스트림이 개통된다
5) 스트림이 개통되면 통신이 가능하다
6) close를 호출하면 스트림이 해제된다
*/

public class EchoServer {
    public static void main(String[] args) throws IOException {
        // 1) 소켓 생성(휴대폰 구매)
        //    ip/port가 필요한데
        //    ip는 현재 Host의 ip를 자동할당
        //    대신 port는 정해줘야 한다(9000)
        ServerSocket serverSocket = new ServerSocket(9000);

        // 2) 클라이언트의 접속을 기다린다
        // accpet()를 호출하면 대기하고 있다가 클라이언트가 접속연결되면
        // accept()는 클라이언트와 연결된 새로운 연결소켓을 반환한다
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
        }

        // 5) 스트림 종료
        pw.close();
        br.close();
        System.out.println("client - server Ended!");
    }

    private static class Member {
        public Member(String junho, String s, String s1) {
        }
    }
}














