/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class UserVocabulary {
    private int userVocabID;
    private User user;
    private String word;
    private String meaning;
    private Date createdAt;

    public int getUserVocabID() { return userVocabID; }
    public void setUserVocabID(int userVocabID) { this.userVocabID = userVocabID; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}