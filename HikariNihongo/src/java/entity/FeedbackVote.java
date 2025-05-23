/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class FeedbackVote {
    private int voteID;
    private Feedback feedback;
    private User user;

    public int getVoteID() { return voteID; }
    public void setVoteID(int voteID) { this.voteID = voteID; }

    public Feedback getFeedback() { return feedback; }
    public void setFeedback(Feedback feedback) { this.feedback = feedback; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
