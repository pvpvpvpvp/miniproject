package controller.server;


import vo.User;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
	private final static int PORT = 9010;
	public static  List<User> caseDate;
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		while (true){
			Socket conSocket = serverSocket.accept();
			InetAddress inetAddr = conSocket.getInetAddress();
			System.out.println("Connect "+inetAddr);
			ServerAction server = new ServerAction(conSocket,inetAddr);
			server.start();
		}
	}
}

