package webapp;

import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Registration")
public class Registration extends HttpServlet{

    public Registration() { }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loginname", request.getParameter("loginname"));
        request.setAttribute("password", request.getParameter("password"));

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String mail = request.getParameter("mail");
        String login = request.getParameter("loginname");
        String password = request.getParameter("password");

        if (firstName.equals("") || lastName.equals("") || mail.equals("") || login.equals("") || password.equals("")) {
            request.setAttribute("errorMessage", "Please, fill out all fields.");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        } else {
            User user = new User(firstName, lastName, mail, login, password);

            try {
                Connection conn = utils.MyUtils.createConnection(request);

                if (utils.DBUtils.addUser(conn, user)) {
                    request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "This login exist.");
                    request.getRequestDispatcher("/registration.jsp").forward(request, response);
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
