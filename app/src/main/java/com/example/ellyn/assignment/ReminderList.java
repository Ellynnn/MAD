 package com.example.ellyn.assignment;

public class ReminderList {
    private String foodName;
    private String foodCategory;
    private String expiryDate;

    public ReminderList(){}

    public ReminderList(String foodName, String foodCategory, String expiryDate) {
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.expiryDate = expiryDate;
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
}
