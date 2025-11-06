package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // 接続情報は環境に合わせて変更してください
    private static final String URL = "jdbc:postgresql://localhost:5433/postgres"; 
    private static final String USER = "postgres";  
    private static final String PASSWORD = "password";  

    public static Connection getConnection() throws SQLException {
        try {
            // PostgreSQL JDBCドライバをロード
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found!", e);
        }

        // DB接続を返す
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}