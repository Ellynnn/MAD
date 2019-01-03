 package com.example.ellyn.assignment;

public class ReminderList {
    private String foodName;
    private String foodCategory;
    private String expiryDate;
    private String remindAt;
    private String reminderID;
    private String userID;

    public ReminderList(){}

    public ReminderList(String foodName, String foodCategory, String expiryDate, String remindAt, String reminderID, String userID) {
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.expiryDate = expiryDate;
        this.remindAt = remindAt;
        this.reminderID = reminderID;
        this.userID = userID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
       this.foodCategory = foodCategory;
    }

    public String getExpiryDate() {
       return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
       this.expiryDate = expiryDate;
   }

    public String getRemindAt() {
        return remindAt;
    }

    public void setRemindAt(String remindAt) {
        this.remindAt = remindAt;
    }

    public String getReminderID() {
        return reminderID;
    }

    public void setReminderID(String reminderID) {
        this.reminderID = reminderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
