package dao;

import java.sql.*;
import model.Vocabulary;
import DB.JDBCConnection;

public class VocabularyDAO {

    public void add(Vocabulary v) throws SQLException {
        String sql = "INSERT INTO Vocabulary (Word, Meaning, Reading, Example) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getWord());
            stmt.setString(2, v.getMeaning());
            stmt.setString(3, v.getReading());
            stmt.setString(4, v.getExample());
            stmt.executeUpdate();
        }
    }

    public void update(Vocabulary v) throws SQLException {
        String sql = "UPDATE Vocabulary SET Word=?, Meaning=?, Reading=?, Example=? WHERE VocabID=?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getWord());
            stmt.setString(2, v.getMeaning());
            stmt.setString(3, v.getReading());
            stmt.setString(4, v.getExample());
            stmt.setInt(5, v.getVocabID());
            stmt.executeUpdate();
        }
    }

    public void delete(int vocabID) throws SQLException {
        String sql = "DELETE FROM Vocabulary WHERE VocabID=?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vocabID);
            stmt.executeUpdate();
        }
    }
}
