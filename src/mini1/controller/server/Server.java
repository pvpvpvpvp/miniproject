package mini1.controller.server;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import mini1.user.User;

public class Server {
	private static int PORT = 9010;
	public static String[] parsePacket(String line) {
		String[] params  = line.split("\\|");
		return params;
 	}
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		Socket conSocket = serverSocket.accept();
		InetAddress inetAddr = conSocket.getInetAddress();
		System.out.println("Connect "+inetAddr);
		
		OutputStream out = conSocket.getOutputStream();
		OutputStreamWriter outW = new OutputStreamWriter(out);
		PrintWriter pw = new PrintWriter(outW);
		
		InputStream in = conSocket.getInputStream();
		InputStreamReader inR = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inR);
		
		OutputStream outfile = new FileOutputStream("User.txt");
		BufferedOutputStream bFOut = new BufferedOutputStream(outfile);
		PrintWriter pwF = new PrintWriter(bFOut);
		
		while(true) {
			// 클라이언트가 보내온 전체 패킷을 수신
			String line = br.readLine();
			if(line ==  null) {
				System.out.println("Disconnect Client");
				break;
			}
			System.out.println("Received Data : "+ line);
			SaveUserData(line,pwF);
		}
		
	}
	public static void SaveUserData(String data, PrintWriter pw) throws IOException {
		pw.println(data);
		pw.flush();
	}
}






















