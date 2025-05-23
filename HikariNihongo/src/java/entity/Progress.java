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
public class Progress {
    private int progressID;
    private User user;
    private Course course;
    private Lesson lesson;
    private int completionPercent; // 0-100
    private Date lastAccessed;

    public int getProgressID() { return progressID; }
    public void setProgressID(int progressID) { this.progressID = progressID; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Lesson getLesson() { return lesson; }
    public void setLesson(Lesson lesson) { this.lesson = lesson; }

    public int getCompletionPercent() { return completionPercent; }
    public void setCompletionPercent(int completionPercent) { this.completionPercent = completionPercent; }

    public Date getLastAccessed() { return lastAccessed; }
    public void setLastAccessed(Date lastAccessed) { this.lastAccessed = lastAccessed; }
}
