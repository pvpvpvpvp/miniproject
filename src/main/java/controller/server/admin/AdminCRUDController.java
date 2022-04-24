package controller.server.admin;

import controller.server.DbConnection;
import sql.admin.AdminCRUD;
import vo.Admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminCRUDController implements adminCRUD {
    Connection con = new DbConnection().getCon();
    Statement statemt;
    {
        try {
            statemt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Admin admin = new Admin();


    @Override
    public void createAdmin() {
        admin.setId("id");
        admin.setGrade(1);
        admin.setPw0("pw");
        admin.setNickname("nickname");
        AdminCRUD adminCRUD = new AdminCRUD(admin);
        try {

            System.out.println(adminCRUD.adminInsert());
            int u = statemt.executeUpdate(adminCRUD.adminInsert());
            System.out.println(u+"작동됬습니다~");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        PreparedStatement preparedStatement = null;
        try {
//            preparedStatement = con.prepareStatement(adminCRUD.adminInsert());
//            int u = preparedStatement.executeUpdate(adminCRUD.adminInsert());
////            con.commit();
//            preparedStatement.close();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listAdmin() {

    }

    @Override
    public void updateAdmin() {

    }

    @Override
    public void deleteAdmin() {

    }

    public static void main(String[] args) {
        AdminCRUDController adminCRUDController = new AdminCRUDController();
        adminCRUDController.createAdmin();
    }
}
