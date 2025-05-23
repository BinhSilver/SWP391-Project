// ForgotPasswordServlet.java
package severlet;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        boolean exist = dao.sendPasswordReset(email);
        if (exist) {
            request.setAttribute("message", "A reset link has been sent to your email.");
        } else {
            request.setAttribute("error", "Email not found.");
        }
        request.getRequestDispatcher("forgot-password.html").forward(request, response);
    }
}
