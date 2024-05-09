package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "developer";
    private static final String PASSWORD = "developer";

    public static final Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        return connection;
    }

}
