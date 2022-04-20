package controller.server;


import user.User;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static controller.server.Server.caseDate;

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
class ServerAction extends Thread{
	public final static  String DB = "User.txt";
	public static boolean lording = false;
	private Socket socket;
	private InetAddress inetAddr;

	ServerAction(Socket socket,InetAddress inetAddr){
		this.socket = socket;
		this.inetAddr=inetAddr;
	}
	@Override
	public void run() {
		try {
			OutputStream outfile = new FileOutputStream(DB,true);
			BufferedOutputStream bFOut = new BufferedOutputStream(outfile);
			PrintWriter pwF = new PrintWriter(bFOut);

			OutputStream out = socket.getOutputStream();
			OutputStreamWriter outW = new OutputStreamWriter(out);
			PrintWriter pw = new PrintWriter(outW);

			InputStream in = socket.getInputStream();
			InputStreamReader inR = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inR);
			ActionController actionController;
//			LoadDataWithIndex(brF);
			while (true) {
				// 클라이언트가 보내온 전체 패킷을 수신
				String line = br.readLine();
				if (false) {
					System.out.println("Disconnect Client");
					break;
				}
				System.out.println("Received Data : " + line);

				switch (line.split(" ")[0]) {
					case "DATA":
						actionController = new ActionController(line, pwF, pw, DB);
						TextSave textSave = new TextSave(actionController);
						Thread s1 = new Thread(textSave);
						s1.start();
						s1.join();
						break;
					case "LIST":
						actionController = new ActionController(Integer.parseInt(line.split(" ")[1]), 20,DB,pw);
						ListSend listSend = new ListSend(actionController);
						Thread s2 = new Thread(listSend);
						s2.start();
						s2.join();
						break;
					case "DELETE":
						if(line.equals("DELETE")) {// 삭제할 값이 안오면 미작동.!
							pw.println("DELETE");
							pw.flush();
							break;
						}
						actionController = new ActionController(line.split(" ")[1],pwF,pw,DB);
						DeleteUser deleteUser = new DeleteUser(actionController);
						Thread s3 = new Thread(deleteUser);
						s3.start();
						s3.join();
						break;
					case "UPDATE":
						actionController = new ActionController(line,pwF,pw,DB);
						UpdateSet updateSet = new UpdateSet(actionController);
						Thread s4 = new Thread(updateSet);
						s4.start();
						s4.join();
						break;
					case "UPDATEDO":
						actionController = new ActionController(line,pwF,pw,DB);
						UpdateUser updateUser = new UpdateUser(actionController);
						Thread s5 = new Thread(updateUser);
						s5.start();
						s5.join();
					case "EXIT":

						break;
				}
			}
		}catch (Exception e){

		}
	}
}

class ActionController{
	public User user = new User();
	public String data;
	public String id;
	public PrintWriter pwf;
	public PrintWriter pw;
	public String DB;
	public int pageIndex,listLimit;

	ActionController(){}

	Object keySave = new Object();
	Object keySend = new Object();
	Object keyLoad = new Object();
	Object keyDelete = new Object();
	Object keyUpdate = new Object();

	ActionController(String data, PrintWriter pwf,PrintWriter pw,String DB){
		this.data = data;
		this.pw = pw;
		this.pwf = pwf;
		this.DB = DB;
	}
	ActionController(int pageIndex,int listLimit,String DB,PrintWriter pw){
		this.pageIndex = pageIndex;
		this.listLimit = listLimit;
		this.DB = DB;
		this.pw = pw;
	}
	void textSave() {
		synchronized (keySave) {
			data = user.ParserToPacket(data).toSave();
			pwf.println(data);
			pwf.flush();
			pw.println("DATA SAVE DONE");
			pw.println("quit");
			pw.flush();
		}
	}
	void loadDataWithIndex() throws FileNotFoundException {
		caseDate = new ArrayList<>();
		synchronized (keyLoad) {
			InputStream inFile = new FileInputStream(DB);
			InputStreamReader inRF = new InputStreamReader(inFile);
			BufferedReader brF = new BufferedReader(inRF);
				User user = new User();
				String st = "";
				while (true) {
					try {
						st = brF.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (st == null) {
						System.out.println("데이터 로드 완료!");
						break;
					}
					caseDate.add(user.PaserToUser(st));
				}
			}
	}
	void sendData() {
		synchronized (keySend) {
			System.out.println("파일 보내기 시작");
			StringBuilder DBDATE = new StringBuilder("");
			int ListLength = caseDate.size();
			String data = "";
			if (pageIndex >= 1) {
				pageIndex--;
			}
			for (int i = pageIndex * listLimit; i < ListLength && i < pageIndex * listLimit + listLimit; i++) {
				data = caseDate.get(i).toSave();
				System.out.println(data);
				DBDATE.append("Index : " + (i + 1) + " USER : " + data + "\n");
			}
			pw.println("Page : " + (pageIndex + 1) + " LastPageIndex : " + (((ListLength-1) / listLimit) + 1) + " listLimit : " + listLimit);
			pw.println(DBDATE);
			pw.println("LIST");
			pw.flush();
		}
	}
	void deleteUser() throws FileNotFoundException {
		synchronized (keyDelete){
			OutputStream outfile = new FileOutputStream(DB);
			BufferedOutputStream bFOut = new BufferedOutputStream(outfile);
			PrintWriter pwf = new PrintWriter(bFOut);
			System.out.println(data+" 삭제를 위한 색인 및 저장 배열 준비");
			for (int i=0; i<caseDate.size();i++) {
				if (caseDate.get(i).getId().equals(data)) {
					caseDate.remove(i);
				}
			}
			for (int i=0; i<caseDate.size();i++) {
				pwf.println(caseDate.get(i).toSave());
				pwf.flush();
			}
			pw.println("DELETE DONE");
			pw.flush();
		}
	}
	void updateAuthCheck(){
		System.out.println(data);
		String[] user= data.split(" ");
		boolean login = true;
		if(user.length>1){
			user=data.split(" ");
			for (int i=0; i<caseDate.size();i++) {
				if (caseDate.get(i).getId().equals(user[1])&&caseDate.get(i).getPw0().equals(user[2])) {
					pw.println("UPDATE SET "+caseDate.get(i).getId());
					pw.flush();
					login=false;
				}
			}
			if (login) {
				pw.println("quit");
				pw.flush();
			}
		}else {
			pw.println("UPDATE");
			pw.flush();
		}

	}
	void updateUser() throws FileNotFoundException {
		synchronized (keyUpdate){
			OutputStream outfile = new FileOutputStream(DB);
			BufferedOutputStream bFOut = new BufferedOutputStream(outfile);
			PrintWriter pwf = new PrintWriter(bFOut);
			System.out.println(data+" 업데이트를 위한 절차 진행");
			String idd = data.split( " ")[1];
			String pwd = data.split(" ")[2];
			for (int i=0; i<caseDate.size();i++) {
				if (caseDate.get(i).getId().equals(idd)) {
					System.out.println("변경 진행"+pwd);
					caseDate.get(i).setPw0(pwd);
				}
			}
			for (int i=0; i<caseDate.size();i++) {
				pwf.println(caseDate.get(i).toSave());
				pwf.flush();
			}
			pw.println("UPDATE DONE");
			pw.flush();
		}
	}

}

class TextSave implements Runnable{
	ActionController actionController;
	TextSave(ActionController actionController){
		this.actionController = actionController;
	}
	@Override
	public void run() {
		actionController.textSave();
	}
}

class ListSend implements Runnable{
	ActionController actionController;
	ListSend(ActionController actionController){
		this.actionController = actionController;
	}
	@Override
	public void run() {
		try {
			actionController.loadDataWithIndex();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		actionController.sendData();
	}
}
class DeleteUser implements Runnable{
	ActionController actionController;
	DeleteUser(ActionController actionController){
		this.actionController = actionController;
	}
	@Override
	public void run() {
		try {
			actionController.loadDataWithIndex();
			actionController.deleteUser();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
class UpdateSet implements Runnable{
	ActionController actionController;
	UpdateSet(ActionController actionController){
		this.actionController = actionController;
	}

	@Override
	public void run() {
		try {
			actionController.loadDataWithIndex();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		actionController.updateAuthCheck();
	}
}
class UpdateUser implements Runnable{
	ActionController actionController;
	UpdateUser(ActionController actionController){
		this.actionController = actionController;
	}

	@Override
	public void run() {
		try {
			actionController.updateUser();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}