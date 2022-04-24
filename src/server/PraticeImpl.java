package server;

import vo.Member;
import vo.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PraticeImpl implements Pratice {

    public  Member member;
    public  Order order;
    int seq =1;
    public Member strat() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("아이디 : ");
        String id = sc.nextLine();
        System.out.println("비밀번호 :");
        String pw = sc.nextLine();

        System.out.println("예약 : 1 | 취소 : 2");

        int choice = sc.nextInt();
        if (choice==1){
            reserve(member);
        }else if(choice == 2){
            cancle(order);
        }
        return new Member(seq++,id, pw);
    }

    public static Connection dbConn;
    @Override
    public void reserve(Member member) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("예약 날짜를 선택해 주세요");
        String order_date = sc.nextLine();
//        String cancle_date = sc.nextLine();
//        String order_state= sc.nextLine();
        System.out.println("가격을 적어주세요");
        int order_price = sc.nextInt();

        Order order = new Order(member.getMember_idx(),order_date,order_price);

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            con = DriverManager.getConnection(url,"SYSTEM","1234");
            System.out.println("연결성공");

            String sql = "insert into order_r values(?,?,?,?,?)"; //? 는prepareStatement 가 알아봐요
            System.out.println(member.getMember_idx()+" "+ member.getId());
            pstmt =con.prepareStatement(sql);

            pstmt.setInt(1,member.getMember_idx());
            pstmt.setString(2,order.getOrder_date());
            pstmt.setString(3,order.getOrder_date());
            pstmt.setString(4,"1");
            pstmt.setInt(5,order.getOrder_price());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB 연결 실패 무언가 잘못됬다.. 드라이버 연결 정보 오류");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("DB 연결 실패 무언가 잘못됬다..  드라이버 클래스 파일 오류");
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("별도의 사유로 연결 실패");
            e.printStackTrace();

        }
        con.close();
        pstmt.close();

    }

    @Override
    public void cancle(Order order) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            con = DriverManager.getConnection(url,"SYSTEM","1234");
            System.out.println("연결성공");
            Scanner sc = new Scanner(System.in);
            System.out.println("없애고 싶은 번호");
            int del_num = sc.nextInt();
            String sql = "delete from order_r where  order_idx=?";
//            System.out.println(member.getMember_idx()+" "+ member.getId());
            pstmt =con.prepareStatement(sql);

            pstmt.setInt(1,del_num);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB 연결 실패 무언가 잘못됬다.. 드라이버 연결 정보 오류");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("DB 연결 실패 무언가 잘못됬다..  드라이버 클래스 파일 오류");
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("별도의 사유로 연결 실패");
            e.printStackTrace();

        }
        con.close();
        pstmt.close();

    }


}
