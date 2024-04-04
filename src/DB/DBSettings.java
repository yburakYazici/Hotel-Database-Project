package DB;

import java.sql.*;

    public class DBSettings {
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=toor");
        }
    }
