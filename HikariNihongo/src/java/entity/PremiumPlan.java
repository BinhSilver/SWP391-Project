/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class PremiumPlan {
    private int planID;
    private String planName;
    private double price;
    private int durationInMonths;
    private String description;

    public int getPlanID() { return planID; }
    public void setPlanID(int planID) { this.planID = planID; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getDurationInMonths() { return durationInMonths; }
    public void setDurationInMonths(int durationInMonths) { this.durationInMonths = durationInMonths; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
