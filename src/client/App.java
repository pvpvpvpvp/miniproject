package client;

import java.sql.*;

public class App {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String userName = "SYSTEM";
        String password = "1234";

        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from order_r");

        resultSet.next();
        String name = resultSet.getString("name");
        System.out.println(name);

        resultSet.close();
        statement.close();
        connection.close();
    }

}