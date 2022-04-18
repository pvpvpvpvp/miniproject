package client;

import controller.server.Onetime;
import user.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
		final static int POTR = 9010;
		final static String IP = "127.0.0.1";
		public static void main(String[] args) throws UnknownHostException, IOException {
			

			Socket clientSocket = new Socket(IP,POTR);

			
			OutputStream out = clientSocket.getOutputStream();
			OutputStreamWriter outW = new OutputStreamWriter(out);
			PrintWriter pw = new PrintWriter(outW);
			
			InputStream in = clientSocket.getInputStream();
			InputStreamReader inR = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inR);


			//:TODO 메뉴가 반복 되게 해야됨!
			User user = new User();
			EXIT:
			while (true) {
				Onetime one = new Onetime();
				user = one.Re();
				// 1번 ""
				// 2번 LIST
				// 3번 EXIT
				String dataSend = one.MenuAction().equals("") ? user.toPacket() : one.MenuAction();
				// 데이터 액션값에 따라서 없다면 데이터 패킷화를 저장 아니라면 액션값을 저장함
				pw.println(dataSend);
				pw.flush();
				System.out.println(dataSend + " 송신 완료");
				if (dataSend.equals("EXIT"))
					break EXIT;
				while (true&!one.MenuAction().equals("")) {  // :TODO: 서버는 TCP 형태로 어떤응답에 대해서도 응답이 존재하게 짤것!
					String data = br.readLine();
					System.out.println("FROM Server "+data);
					if (data.equals("quit")) {
						System.out.println("마무리 문자 읽음 종료");
						break;
					}
				}
				one.ResetMenuAction();// 값 리셋

			}
			System.out.println("end");
			br.close();
			pw.close();
		}

}


