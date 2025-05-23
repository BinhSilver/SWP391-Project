/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class QuizResult {
    private int resultID;
    private User user;
    private Quiz quiz;
    private int score;
    private Date takenAt;

    public int getResultID() { return resultID; }
    public void setResultID(int resultID) { this.resultID = resultID; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public Date getTakenAt() { return takenAt; }
    public void setTakenAt(Date takenAt) { this.takenAt = takenAt; }
}
