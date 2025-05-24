package dao;

import java.sql.*;
import model.Kanji;
import DB.JDBCConnection;

public class KanjiDAO {

    public void add(Kanji k) throws SQLException {
        String sql = "INSERT INTO Kanji (Character, Onyomi, Kunyomi, Meaning, StrokeCount, LessonID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, k.getCharacter());
            stmt.setString(2, k.getOnyomi());
            stmt.setString(3, k.getKunyomi());
            stmt.setString(4, k.getMeaning());
            stmt.setInt(5, k.getStrokeCount());
            stmt.setInt(6, k.getLessonID());
            stmt.executeUpdate();
        }
    }

    public void update(Kanji k) throws SQLException {
        String sql = "UPDATE Kanji SET Character=?, Onyomi=?, Kunyomi=?, Meaning=?, StrokeCount=?, LessonID=? WHERE KanjiID=?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, k.getCharacter());
            stmt.setString(2, k.getOnyomi());
            stmt.setString(3, k.getKunyomi());
            stmt.setString(4, k.getMeaning());
            stmt.setInt(5, k.getStrokeCount());
            stmt.setInt(6, k.getLessonID());
            stmt.setInt(7, k.getKanjiID());
            stmt.executeUpdate();
        }
    }

    public void delete(int kanjiID) throws SQLException {
        String sql = "DELETE FROM Kanji WHERE KanjiID=?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, kanjiID);
            stmt.executeUpdate();
        }
    }
}
