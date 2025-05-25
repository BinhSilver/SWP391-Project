package dao;

import java.sql.*;

import DB.JDBCConnection;
import java.util.ArrayList;
import java.util.List;
import model.Course;

public class CoursesDAO {

    public void add(Course c) throws SQLException {
        String sql = "INSERT INTO [dbo].[Courses] (Title, Description, IsHidden) VALUES (?, ?, ?)";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getDescription());
            stmt.setBoolean(3, c.isIsHidden());
            stmt.executeUpdate();
        }
    }

    public void update(Course c) throws SQLException {
        String sql = "UPDATE [dbo].[Courses] SET Title=?, Description=?, IsHidden=? WHERE CourseID=?";
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
        String sql = "DELETE FROM [dbo].[Courses] WHERE CourseID=?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseID);
            stmt.executeUpdate();
        }
    }
        public List<Course> getAllCoursesforchatbox() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Courses]";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("CourseID"));
               course.setTitle(rs.getNString("Title"));
               course.setDescription(rs.getString("Description"));
                courses.add(course);
            }
        }
        return courses;
    }
    public static void main(String[] args) throws SQLException {
        CoursesDAO testcourse = new CoursesDAO();
        test.Testcase.printlist(testcourse.getAllCoursesforchatbox());
    }
}
