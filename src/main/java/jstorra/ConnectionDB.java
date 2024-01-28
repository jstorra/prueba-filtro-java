package jstorra;

import java.sql.*;

public abstract class ConnectionDB {
    private static String url;
    private static String user;
    private static String password;
    private static Connection con;
    
    public static Connection MySQLConnection() {
        url = "jdbc:mysql://localhost:3306/db_java";
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
