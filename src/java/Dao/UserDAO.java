package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;
import DB.JDBCConnection;

public class UserDAO {

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO [dbo].[Users] (RoleID, Email, PasswordHash, GoogleID, FullName, IsActive, IsLocked) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getRoleID());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getGoogleID());
            stmt.setString(5, user.getFullName());
            stmt.setBoolean(6, user.isActive());
            stmt.setBoolean(7, user.isLocked());
            stmt.executeUpdate();
        }
    }

    public User getUserById(int userID) throws SQLException {
        String sql = "SELECT * FROM [dbo].[Users] WHERE UserID = ?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setRoleID(rs.getInt("RoleID"));
                user.setEmail(rs.getString("Email"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setGoogleID(rs.getString("GoogleID"));
                user.setFullName(rs.getString("FullName"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setActive(rs.getBoolean("IsActive"));
                user.setLocked(rs.getBoolean("IsLocked"));
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Users]";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setRoleID(rs.getInt("RoleID"));
                user.setEmail(rs.getString("Email"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setGoogleID(rs.getString("GoogleID"));
                user.setFullName(rs.getString("FullName"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setActive(rs.getBoolean("IsActive"));
                user.setLocked(rs.getBoolean("IsLocked"));
                users.add(user);
            }
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE [dbo].[Users] SET RoleID = ?, Email = ?, PasswordHash = ?, GoogleID = ?, FullName = ?, IsActive = ?, IsLocked = ? WHERE UserID = ?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getRoleID());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getGoogleID());
            stmt.setString(5, user.getFullName());
            stmt.setBoolean(6, user.isActive());
            stmt.setBoolean(7, user.isLocked());
            stmt.setInt(8, user.getUserID());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int userID) throws SQLException {
        String sql = "DELETE FROM [dbo].[Users] WHERE UserID = ?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.executeUpdate();
        }
    }
    
    public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT userID, roleID, email, passwordHash, googleID, fullName, createdAt, isActive, isLocked FROM Users WHERE email = ?";

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setRoleID(rs.getInt("roleID"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setGoogleID(rs.getString("googleID"));
                user.setFullName(rs.getString("fullName"));
                user.setCreatedAt(rs.getDate("createdAt"));
                user.setActive(rs.getBoolean("isActive"));
                user.setLocked(rs.getBoolean("isLocked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public boolean createNewUser(String email, String rawPassword) {
    String sql = "INSERT INTO Users (RoleID, Email, PasswordHash) VALUES (?, ?, ?)";

    try (Connection conn = JDBCConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        if (conn == null) {
            System.out.println("Connection null!");
            return false;
        }

        pstmt.setInt(1, 1); // role mặc định
        pstmt.setString(2, email);
        pstmt.setString(3, rawPassword); // lưu mật khẩu thô vào PasswordHash

        int rowsInserted = pstmt.executeUpdate();
        return rowsInserted > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}




 
    public static void main(String[] args) throws SQLException {
        UserDAO a = new UserDAO();
        test.Testcase.printlist(a.getAllUsers());
       
    }
}
