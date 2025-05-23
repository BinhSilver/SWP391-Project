// LoginServlet.java
package severlet;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private DAO dao = new DAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Kiểm tra đăng nhập với dao
        boolean valid = dao.checkLogin(email, password);
        if (valid) {
            // Lưu session user
            request.getSession().setAttribute("user", email);
            response.sendRedirect("dashboard.jsp"); // hoặc trang chính
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
