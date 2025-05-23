/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Answer {
    private int answerID;
    private Question question;
    private String answerText;
    private int answerNumber;

    public int getAnswerID() { return answerID; }
    public void setAnswerID(int answerID) { this.answerID = answerID; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }

    public int getAnswerNumber() { return answerNumber; }
    public void setAnswerNumber(int answerNumber) { this.answerNumber = answerNumber; }
}
