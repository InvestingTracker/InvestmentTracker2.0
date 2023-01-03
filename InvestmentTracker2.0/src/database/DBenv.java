package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBenv {
    public static Connection getConnection() throws SQLException {
        String url="jdbc:mysql://localhost:3306/investmenttracker";
        String username = "root";
        String password = "Nbhsj@97203";
        Connection connection = DriverManager.getConnection(url,username,password);

        return connection;
    }

}
