package dao;

import context.DBContext;
import java.sql.*;

public class DAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
//Check Login
    public boolean checkLogin(String email, String password) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND PasswordHash = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password); // Bạn nên mã hóa password khi lưu và kiểm tra thực tế
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }
//Dang Ky
    public boolean registerUser(String fullname, String email, String password) {
        // Kiểm tra email tồn tại
        String checkSql = "SELECT * FROM Users WHERE Email = ?";
        String insertSql = "INSERT INTO Users(RoleID, Email, PasswordHash, FullName) VALUES (1, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(checkSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) return false; // Email tồn tại
            closeAll();

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(insertSql);
            ps.setString(1, email);
            ps.setString(2, password); // Nên mã hóa
            ps.setString(3, fullname);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }
// Quen mat khau
    public boolean sendPasswordReset(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Ở đây bạn nên gửi mail reset password thực tế
                System.out.println("Send reset password link to " + email);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    private void closeAll() {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}
