package controller.server;


import user.User;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int PORT = 9010;
	public static String[] parsePacket(String line) {
		String[] params  = line.split("\\|");
		return params;
 	}
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);

		OutputStream outfile = new FileOutputStream("User.txt",true);
		BufferedOutputStream bFOut = new BufferedOutputStream(outfile);
		PrintWriter pwF = new PrintWriter(bFOut);

		InputStream inFile = new FileInputStream("User.txt");
		InputStreamReader inRF = new InputStreamReader(inFile);
		BufferedReader brF = new BufferedReader(inRF);

		Socket conSocket = serverSocket.accept();
		InetAddress inetAddr = conSocket.getInetAddress();
		System.out.println("Connect "+inetAddr);

		OutputStream out = conSocket.getOutputStream();
		OutputStreamWriter outW = new OutputStreamWriter(out);
		PrintWriter pw = new PrintWriter(outW);

		InputStream in = conSocket.getInputStream();
		InputStreamReader inR = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inR);
		//:TODO 클라이언트와 통신유지!
		while(true) {

			// 클라이언트가 보내온 전체 패킷을 수신
			String line = br.readLine();
			if(false) {
				System.out.println("Disconnect Client");
				break;
			}
			System.out.println("Received Data : "+ line);
			flushData(line.split(" ")[0],pw,brF);
			SaveUserData(line,pwF,pw);
		}
		
	}
	public static void SaveUserData(String data, PrintWriter pwf, PrintWriter pw) throws IOException {
		if(data.equals("LIST")){
			System.out.println("리스트 입력 받음 작동안함!");
			return;
		}
		User user = new User();
		data = user.Parser(data).toSave();
		pwf.println(data);
		pwf.flush();
		pw.println("DATA SAVE DONE");
		pw.flush();
	}
	public static void flushData(String type,PrintWriter pw,BufferedReader brF) throws IOException{
		//:TODO 최초 1회만 읽고 그 다음서버가 저장하고 있는 데이터를 보내준다.
		if(type.equals("LIST"))
		{
			String st ="";
			while (true){
				st=brF.readLine();
				if(st==null){
					System.out.println("파일 다 읽음! 마무리 문자 붙히기");
					pw.println("quit");
					pw.flush();
					break;
				}
				System.out.println(st);
				pw.println(st);
				pw.flush();
			}
		}
	}
}






















