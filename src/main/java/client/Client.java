package client;

import vo.User;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
		final static int PORT = 9010;
		final static String IP = "127.0.0.1";
		private static StringBuilder sb;
		static Scanner sc = new Scanner(System.in);
		public static void main(String[] args) throws IOException {

			Socket clientSocket = new Socket(IP,PORT);
			showBanner();
			OutputStream out = clientSocket.getOutputStream();
			OutputStreamWriter outW = new OutputStreamWriter(out);
			PrintWriter pw = new PrintWriter(outW);
			
			InputStream in = clientSocket.getInputStream();
			InputStreamReader inR = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inR);


			//:TODO 메뉴가 반복 되게 해야됨!

			while (true) {
				User user = new User();
				Onetime one = new Onetime(sc);
				user = one.Re();
				// 1번 DATA
				// 2번 LIST
				// 3번 EXIT
				String dataSend = one.MenuAction().equals("DATA") ? user.toPacket() : one.MenuAction();
				// 데이터 액션값에 따라서 없다면 데이터 패킷화를 저장 아니라면 액션값을 저장함
				pw.println(dataSend);
				pw.flush();
				System.out.println(dataSend + " 송신 완료");
				user = one.ResetUser();
				if (dataSend.equals("EXIT"))
					break;
				 // :TODO: 서버는 TCP 형태로 어떤응답에 대해서도 응답이 존재하게 짤것!
				controller(br,user,pw,one);

				one.ResetMenuAction();// 값 리셋
			}
			System.out.println("end");
			br.close();
			pw.close();
		}

	private static void controller(BufferedReader br,User user, PrintWriter pw, Onetime one) throws IOException {
		while (true) {
			String data = br.readLine();
			System.out.println("FROM Server " + data);
			if (data.equals("quit")) {
				System.out.println("마무리 문자 읽음 종료");
				break;
			}
			if (data.equals("LIST")) {
				if (InputIndex(pw)) break;
			}
			if (data.equals("DELETE")) {
				InputDeleteForMember(pw);
			}
			if (data.equals("DELETE DONE") | data.equals("UPDATE DONE"))
				break;
			if (data.equals("UPDATE")) {
				UpdateMemberPw(pw);
			}
			if (data.contains("UPDATE SET")) { // TODO 세션으로이나 JWT로 로그인 기능 구현해야됨.!
				InputUpdateMeberPw(pw, one, data);
			}
		}
	}

	private static void InputUpdateMeberPw(PrintWriter pw, Onetime one, String data) {
		User user;
		System.out.printf("변경할 ");
		one.UserPasswd(sc);
		user = one.Re();
		while (true) {
			if (user.getPw0() == null) {
				one.UserPasswd(sc);
			} else {
				break;
			}
		}
		pw.println("UPDATEDO " + data.split(" ")[2] + " " + user.getPw0());
		pw.flush();
	}

	private static void UpdateMemberPw(PrintWriter pw) {
		System.out.println("아이디 입력해주세요");
		String answer = sc.nextLine();
		System.out.println("비밀번호를 입력해주세요");
		answer += " " + sc.nextLine();
		pw.println("UPDATE " + answer);
		pw.flush();
	}

	private static void InputDeleteForMember(PrintWriter pw) {
		System.out.println("삭제할 아이디 입력");
		String answer = sc.nextLine();
		pw.println("DELETE " + answer);
		pw.flush();
	}

	private static boolean InputIndex(PrintWriter pw) {
		System.out.println("인덱스 입력  종료는 exit 입력");
		String answer = sc.nextLine();
		if (answer.equals("exit"))
			return true;
		pw.println("LIST " + answer);
		pw.flush();
		return false;
	}

	public static void showBanner() {
		sb = new StringBuilder();

		sb.append(" ___  ___  _______   ________ _________  ________     \n");
		sb.append("|\\  \\|\\  \\|\\  ___ \\ |\\   ____\\\\___   ___\\\\   __  \\    \n");
		sb.append("\\ \\  \\\\\\  \\ \\   __/|\\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\   \n");
		sb.append(" \\ \\   __  \\ \\  \\_|/_\\ \\  \\       \\ \\  \\ \\ \\  \\\\\\  \\  \n");
		sb.append("  \\ \\  \\ \\  \\ \\  \\_|\\ \\ \\  \\____   \\ \\  \\ \\ \\  \\\\\\  \\ \n");
		sb.append("   \\ \\__\\ \\__\\ \\_______\\ \\_______\\  \\ \\__\\ \\ \\_______\\\n");
		sb.append("    \\|__|\\|__|\\|_______|\\|_______|   \\|__|  \\|_______|\n");

		System.out.println(sb.toString());
	}
}


