// RegisterServlet.java
package severlet;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean success = dao.registerUser(fullname, email, password);
        if (success) {
            response.sendRedirect("login.html");
        } else {
            request.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("register.html").forward(request, response);
        }
    }
}
