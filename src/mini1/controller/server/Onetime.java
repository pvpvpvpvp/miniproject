package mini1.controller.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import mini1.user.User;

public class Onetime {
	 
	public static User user = new User();

	public Onetime() throws IOException {
		

		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("안녕하세요?");
		System.out.println("1번 회원가입 \n 2번 조회");
		String key = sc.nextLine();
		loop: 
		while(true) {
			System.out.println("안녕하세요?");
			System.out.println("1번 회원가입 \n 2번 조회");
			key = sc.nextLine();
			switch (key) {
			case "1":
				user=join(sc);
				if(user.getPw1()!=null) {
					break loop;
				}
				break;
			case "2":
				// 조회 
				List();
				break;
			default:
				break loop;
			}
		}
		
		sc.close();
		
	}
	public static void List() throws IOException {
		InputStream in = new FileInputStream("user.txt");
		BufferedInputStream bIn = new BufferedInputStream(in);
		DataInputStream dIn = new DataInputStream(bIn);
		
		while(true) {
			String list = dIn.readLine();
			if (list==null) {
				break;
			}
			user = user.Parser(list);
			System.out.println(user.toString());
		}
			
	}
	public static User join(Scanner sc) throws IOException { // 리턴 타입을 유저 객체로!
//		System.out.println(user.toString());
		if(user.getId()==null) {
			System.out.println("아이디를 입력해주세요");
			String id0 = sc.nextLine(); // 나중에 아이디는 객체화
			String idPatten = "^[a-z]+[a-z0-9]{5,19}$";// 정규식 적을껏
			boolean checkedId =  id0.matches(idPatten);
			if(checkedId) {
				System.out.println("통과~");
				user.setId(id0);
			}else { 
				System.out.println("올바르지 않은 아이디 입니다.!!");
				return user;
			}
		}
		if(user.getPw0()==null) {
			System.out.println("비밀번호를 입력해주세요!");
			String passwd0 = sc.nextLine(); // 나중에 아이디는 객체화
			String passwdPatten = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{8,16}$";// 정규식 적을껏
			boolean checkedPasswd0 =  passwd0.matches(passwdPatten);
			if(checkedPasswd0) {
				user.setPw0(passwd0);
	//			System.out.println("통과~");
			}else {
				System.out.println("올바르지 비밀번호 형식입니다.!!");
				return user;
			}
			System.out.println("다시 비밀번호를 입력해주세요!");
			String passwd1= sc.nextLine(); // 나중에 아이디는 객체화
			if(passwd0.equals(passwd1))
			{
				user.setPw1(passwd1);
			}else {
				System.out.println("처음 입력한 비밀번호와 다릅니다!");
				user.setPw0(null);
			}
		}
		return user;
	}
	
	public User Re() {
		// TODO Auto-generated method stub
		return user;
	}
}


