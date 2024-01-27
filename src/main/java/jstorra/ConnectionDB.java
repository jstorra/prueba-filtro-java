package jstorra;

import java.sql.*;

public class ConnectionDB {
    private static String url;
    private static String user;
    private static String password;
    
    public static Connection con;
    
    public static Connection MySQLConnection() {
        url = "jdbc:mysql://localhost:3306/db_huansync";
        user = "root";
        password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
