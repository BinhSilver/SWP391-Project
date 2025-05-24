package dao;

import java.sql.*;
import model.Course;
import DB.JDBCConnection;

public class CoursesDAO {

    public void add(Course c) throws SQLException {
        String sql = "INSERT INTO Courses (Title, Description, IsHidden) VALUES (?, ?, ?)";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getDescription());
            stmt.setBoolean(3, c.isIsHidden());
            stmt.executeUpdate();
        }
    }

    public void update(Course c) throws SQLException {
        String sql = "UPDATE Courses SET Title=?, Description=?, IsHidden=? WHERE CourseID=?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getDescription());
            stmt.setBoolean(3, c.isIsHidden());
            stmt.setInt(4, c.getCourseID());
            stmt.executeUpdate();
        }
    }

    public void delete(int courseID) throws SQLException {
        String sql = "DELETE FROM Courses WHERE CourseID=?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseID);
            stmt.executeUpdate();
        }
    }
}
