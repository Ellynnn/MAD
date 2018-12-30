package com.example.ellyn.assignment;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "FoodSaviorDatabase";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUser = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, username VARCHAR, phone VARCHAR, email VARCHAR, password VARCHAR)";
        String sqlReminder = "CREATE TABLE reminder(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, name VARCHAR, expiry_date DATE, remind_at DATE, user_id INTEGER, FOREIGN KEY(user_id) REFERENCES user(id))";

        sqLiteDatabase.execSQL(sqlUser);
        sqLiteDatabase.execSQL(sqlReminder);
    }

    public boolean addUser(String name, String username, String phone, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues UserContentValues = new ContentValues();
        UserContentValues.put("name", name);
        UserContentValues.put("username", username);
        UserContentValues.put("phone", phone);
        UserContentValues.put("email", email);
        UserContentValues.put("password", password);
        db.insert("user", null, UserContentValues);
        db.close();
        return true;
    }

    //public boolean modifyUser(int id, String name, String username, String phone, String email, String password) {
    //    SQLiteDatabase db = getWritableDatabase();
    //    ContentValues UserContentValues = new ContentValues();
    //    UserContentValues.put("name", name);
    //    UserContentValues.put("username", username);
    //    UserContentValues.put("phone", phone);
    //    UserContentValues.put("email", email);
    //    UserContentValues.put("password", password);
    //    db.update("user", UserContentValues, "user_id=" + id, null);
    //    //db.insert("user", null, UserContentValues);
    //    db.close();
    //    return true;
    //}

    public boolean addReminder(String category, String name, String expiry_date, String remind_at) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues ReminderContentValues = new ContentValues();
        ReminderContentValues.put("name", category);
        ReminderContentValues.put("username", name);
        ReminderContentValues.put("phone", expiry_date);
        ReminderContentValues.put("email", remind_at);
        db.insert("reminder", null, ReminderContentValues);
        db.close();
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlUser = "DROP TABLE IF EXISTS user";
        String sqlReminder = "DROP TABLE IF EXISTS reminder";

        sqLiteDatabase.execSQL(sqlUser);
        sqLiteDatabase.execSQL(sqlReminder);

        onCreate(sqLiteDatabase);
    }
}

