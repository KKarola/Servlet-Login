package connectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String dbName = "simplelogin";
        String userName = "root";
        String password = "";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
        throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        String connURL = "jdbc:mysql://" + hostName + "/" + dbName;
        Connection conn = DriverManager.getConnection(connURL, userName, password);
        return conn;
    }

    public static void closeMySQLConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception exc) {
            System.out.println("Error: " + exc);
        }
    }
}



