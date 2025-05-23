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
public class UserPremium {
    private User user;
    private PremiumPlan plan;
    private Date startDate;
    private Date endDate;

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public PremiumPlan getPlan() { return plan; }
    public void setPlan(PremiumPlan plan) { this.plan = plan; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}