package com.example.ellyn.assignment;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Reminder {
    public String reminderID;
    public String foodCategory;
    public String foodName;
    public String expiryDate;
    public String remindAt;

    public Reminder(String reminderID, String foodCategory, String foodName, String expiryDate, String remindAt){
        this.reminderID = reminderID;
        this.foodCategory = foodCategory;
        this.foodName = foodName;
        this.expiryDate = expiryDate;
        this.remindAt = remindAt;
    }
}
