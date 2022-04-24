package controller.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String SERVER_IP="13.52.44.60";
    private static Connection con;
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@"+SERVER_IP+":1521:xe";
            String id = "hecto";
            String pass = "hecto";
            {
                try {
                    con = DriverManager.getConnection(url, id, pass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public DbConnection(){};

    public Connection getCon() {
        return con;
    }
}
