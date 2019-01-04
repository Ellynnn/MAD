package com.example.ellyn.assignment;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Reminder {
    public String reminderID;
    public String foodCategory;
    public String foodName;
    public String expiryDate;
    public String remindAt;
    public String userID;
    public String remindDate;

    public Reminder(String reminderID, String foodCategory, String foodName, String expiryDate, String remindAt, String remindDate, String userID){
        this.reminderID = reminderID;
        this.foodCategory = foodCategory;
        this.foodName = foodName;
        this.expiryDate = expiryDate;
        this.remindAt = remindAt;
        this.userID = userID;
        this.remindDate = remindDate;
    }
}
