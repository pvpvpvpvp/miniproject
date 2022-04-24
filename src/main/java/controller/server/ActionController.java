package controller.server;

import vo.User;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static controller.server.Server.caseDate;
// 서버에 기능을 구현한다.
// admin 기능 붙히기
public class ActionController {
        public User user = new User();
        public String data;
        public PrintWriter pwf;
        public PrintWriter pw;

        Connection con = new DbConnection().getCon();
        public String DB;
        DbConnection de = new DbConnection();
        Statement statemt = con.createStatement();
        public int pageIndex,listLimit;
        Object keySave = new Object();
        Object keySend = new Object();
        Object keyLoad = new Object();
        Object keyDelete = new Object();
        Object keyUpdate = new Object();
        Object keyAuthCheck = new Object();

        ActionController(String data, PrintWriter pwf,PrintWriter pw,String DB) throws SQLException {
            this.data = data;
            this.pw = pw;
            this.pwf = pwf;
            this.DB = DB;
        }
        ActionController(int pageIndex,int listLimit,String DB,PrintWriter pw) throws SQLException {
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
            System.out.println(con);
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
        // 유저의 아이디 비밀번호 체크 (유사 로그인)
        void updateAuthCheck(){
            synchronized (keyAuthCheck) {
                String[] user = data.split(" ");
                boolean login = true;
                if (user.length > 1) {
                    user = data.split(" ");
                    for (int i = 0; i < caseDate.size(); i++) {
                        if (caseDate.get(i).getId().equals(user[1]) && caseDate.get(i).getPw0().equals(user[2])) {
                            pw.println("UPDATE SET " + caseDate.get(i).getId());
                            pw.flush();
                            login = false;
                        }
                    }
                    if (login) {
                        pw.println("quit");
                        pw.flush();
                    }
                } else {
                    pw.println("UPDATE");
                    pw.flush();
                }
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
                    if (caseDate.get(i).getId().equals(idd)&&!(pwd==null)) {
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
