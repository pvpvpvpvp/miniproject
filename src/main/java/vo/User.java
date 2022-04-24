package vo;

import lombok.Data;

@Data
public class User {
	//TODO : 유저 객체 만들기
	private String id;
	private String pw0;
	private String pw1;
	
	public User() {
	}
	public User(String id,String pw0, String pw1){
		setId(id);
		setPw0(pw0);
		setPw1(pw1);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw0() {
		return pw0;
	}
	public void setPw0(String pw0) {
		this.pw0 = pw0;
	}
	public String getPw1() {
		return pw1;
	}
	public void setPw1(String pw1) {
		this.pw1 = pw1;
	}
	
	@Override
	public String toString() {
		String user = "아이디 : "+id+" 비밀번호 : "+pw0;
		return user;
	}
	public String toPacket() {
		String user = "DATA "+id+" "+pw0+" "+pw1;
		return user;
	}
	public String toSave(){
		String user =id+" "+pw0;
		return user;
	}
	public User ParserToPacket(String data) {
		String[] userData = new String[4];
		userData = data.split(" ");	
		User user = new User(userData[1],userData[2],userData[3]);
		return user;
	}
	public User PaserToUser(String data){
		String[] userData = new String[3];
		userData = data.split(" ");
		User user = new User(userData[0],userData[1],userData[1]);
		return user;
	}
}
