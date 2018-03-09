package utils;

import appLayer.User;
import connectionUtils.MySQLConnection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class MyUtils {
    public static final String CONNECTION = "CONNECTION";

    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(CONNECTION, conn);
    }

    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(CONNECTION);
        return conn;
    }

    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static Connection createConnection(HttpServletRequest request)
            throws ClassNotFoundException, SQLException {

        Connection conn = MySQLConnection.getMySQLConnection();
        MyUtils.storeConnection(request, conn);
        return MyUtils.getStoredConnection(request);
    }

}
