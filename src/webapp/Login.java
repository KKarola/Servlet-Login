package webapp;

import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {

    public Login() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", request.getParameter("loginname"));
        request.setAttribute("password", request.getParameter("password"));

        User user;
        String userName = request.getParameter("loginname");
        String password = request.getParameter("password");

        if (userName.equals("") || password.equals("")) {
            request.setAttribute("errorMessage", "Required login and password. Try again.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            try {
                Connection conn = utils.MyUtils.createConnection(request);
                user = utils.DBUtils.findUser(conn, userName, password);

                if (user != null) {
                    HttpSession session = request.getSession();
                    utils.MyUtils.storeLoginedUser(session, user);
                    request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Invalid login or password. Try again.");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } catch (SQLException | ClassNotFoundException exc) {
                System.out.println("Error: " + exc);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("Login-name" + request.getParameter("loginname") + "Password" + request.getParameter("password"));
    }

}
