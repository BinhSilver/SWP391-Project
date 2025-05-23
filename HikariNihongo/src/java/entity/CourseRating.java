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
public class CourseRating {
    private int ratingID;
    private User user;
    private Course course;
    private int rating; // 1-5
    private String comment;
    private Date ratedAt;

    public int getRatingID() { return ratingID; }
    public void setRatingID(int ratingID) { this.ratingID = ratingID; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Date getRatedAt() { return ratedAt; }
    public void setRatedAt(Date ratedAt) { this.ratedAt = ratedAt; }
}