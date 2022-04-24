package client;

import vo.User;

import java.io.IOException;
import java.util.Scanner;

public class Onetime {
	 
	public static User user = new User();
	public static String MENUACTION ="";
	public static String[] MENULIST = {"","DATA","LIST 0","DELETE","UPDATE","EXIT"};

	public Onetime(Scanner sc) throws IOException {

		System.out.println("안녕하세요?");
		System.out.println("1번 회원가입 \n2번 조회 \n3번 삭제\n4번 비밀번호 변경\n5번 종료");
		String key ="";
		key = sc.nextLine();
		loop: 
		while(true) {
			switch (key) {
			case "1":
				user=join(sc);
				MENUACTION=MENULIST[Integer.parseInt(key)];
				if(user.getPw1()!=null) {
					break loop;
				}
				break;
				case "2": case "3": case "4": case "5": // 응답 절차를 서버로 넘겨버림!
				// 조회 
				MENUACTION=MENULIST[Integer.parseInt(key)];
				break loop;
			default:
				break loop;
			}
		}
		
//		sc.close();
//		br.close();
		
	}
	public String MenuAction(){
		return MENUACTION;
	}
	public void ResetMenuAction(){
		MENUACTION="";
	}
	public User ResetUser(){ return user = new User();}


	public static User join(Scanner sc) throws IOException { // 리턴 타입을 유저 객체로!
		if(user.getId()==null) {
			System.out.println("아이디를 입력해주세요");
			String id0 = sc.nextLine(); // 나중에 아이디는 객체화
			String idPatten = "^[a-z]+[a-z0-9]{5,19}$";// 정규식
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
			return user=UserPasswd(sc);
		}
		return user;
	}
	public static User UserPasswd(Scanner sc){
		System.out.println("비밀번호를 입력해주세요!");
		String passwd0 = sc.nextLine(); // 나중에 아이디는 객체화
		String passwdPatten = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{8,16}$";// 정규식 적을것
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
		return user;
	}
	
	public User Re() {
		return user;
	}
}


