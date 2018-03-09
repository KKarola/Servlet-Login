package utils;

import appLayer.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    public static User findUser(Connection conn, String userName, String password) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        User user = null;
        String query = "SELECT login, password FROM register WHERE login = ? AND password = ? ";

        try {
            stm = conn.prepareStatement(query);
            stm.setString(1, userName);
            stm.setString(2, password);

            rs = stm.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setLogin(userName);
                user.setPassword(password);
            }

            rs.close();
            conn.close();

        } catch (SQLException exc) {
            System.out.println("Error: " + exc);
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) { }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception ex) { }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) { }
        }
        return user;
    }
}
