package com.example.ellyn.assignment;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String userID;
    public String name;
    public String username;
    public String phone;
    public String email;
    public String password;

    public User(String userID, String name, String username, String phone, String email, String password){
        this.userID = userID;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

}