package mini1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import mini1.controller.server.Onetime;
import mini1.user.User;

public class Client {
		final static int POTR = 9010;
		final static String IP = "127.0.0.1";
		public static void main(String[] args) throws UnknownHostException, IOException {
			

			Socket clientSocket = new Socket(IP,POTR);
			User user = new User();
			Onetime one = new Onetime();
			user = one.Re();
			
			OutputStream out = clientSocket.getOutputStream();
			OutputStreamWriter outW = new OutputStreamWriter(out);
			PrintWriter pw = new PrintWriter(outW);
			
			InputStream in = clientSocket.getInputStream();
			InputStreamReader inR = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inR);
		
			pw.println(user.toString());
			pw.flush();
			System.out.println(user.toPacket()+" 송신 완료");
			while(true) {
				if(br.readLine()==null){
					break;
				}
				String packet = user.toString();
				pw.println(packet);
				pw.flush();
			}
			System.out.println("end");
			br.close();
			pw.close();
		}

}


