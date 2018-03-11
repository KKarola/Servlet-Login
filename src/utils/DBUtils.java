package utils;

import appLayer.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    public static User findUser(Connection conn, String login, String password) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        User user = null;
        String query = "SELECT login, password FROM simplelogin WHERE login = ? AND password = ? ";

        try {
            stm = conn.prepareStatement(query);
            stm.setString(1, login);
            stm.setString(2, password);

            rs = stm.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setLogin(login);
                user.setPassword(password);
            }

            rs.close();
            conn.close();

        } catch (SQLException exc) {
            System.out.println("Error: " + exc);
            try {
                if (rs != null) { rs.close(); }
            } catch (Exception ex) { }
            try {
                if (stm != null) { stm.close(); }
            } catch (Exception ex) { }
            try {
                if (conn != null) { conn.close(); }
            } catch (Exception ex) { }
        }
        return user;
    }

    public static boolean addUser(Connection conn, User user) {
        PreparedStatement stm = null;
        boolean adding = false;

        if (!checkLogin(conn, user)) {
            String query = "INSERT INTO simplelogin (login, password, firstname, lastname, mail) VALUES (?, ?, ?, ?, ?)";

            try {
                stm = conn.prepareStatement(query);
                stm.setString(1, user.getLogin());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getFirstName());
                stm.setString(4, user.getLastName());
                stm.setString(5, user.getMail());

                stm.execute();
                stm.close();
                conn.close();
                adding = true;

            } catch (SQLException exc) {
                System.out.println("Error: " + exc);
                try {
                    if (stm != null) { stm.close(); }
                } catch (Exception ex) { }
                try {
                    if (conn != null) { conn.close(); }
                } catch (Exception ex) { }
            }
        }
        return adding;
    }

    public static boolean checkLogin(Connection conn, User user) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        String query = "SELECT login FROM simplelogin";
        boolean existLogin = false;

        try {
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                if (user.getLogin().equals(rs.getString(1))) {
                    existLogin = true;
                }
            }

        } catch (SQLException exc) {
            System.out.println("Error: " + exc);
            try {
                if (rs != null) { rs.close(); }
            } catch (Exception ex) { }
            try {
                if (stm != null) { stm.close(); }
            } catch (Exception ex) { }
            try {
                if (conn != null) { conn.close(); }
            } catch (Exception ex) { }
        }
        return existLogin;
    }
}
