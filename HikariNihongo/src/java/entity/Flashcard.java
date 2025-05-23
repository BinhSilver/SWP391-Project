/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Flashcard {
    private int flashcardID;
    private User user;
    private String title;

    public int getFlashcardID() { return flashcardID; }
    public void setFlashcardID(int flashcardID) { this.flashcardID = flashcardID; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}