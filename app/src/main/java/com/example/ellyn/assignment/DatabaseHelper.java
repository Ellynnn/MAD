package com.example.ellyn.assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "foodsavior.db";
    public static final String TABLE1_NAME = "reminder_table";
    public static final String TABLE1_COL1 = "REMINDER_ID";
    public static final String TABLE1_COL2 = "CATEGORY";
    public static final String TABLE1_COL3 = "NAME";
    public static final String TABLE1_COL4 = "EXPIRY_DATE";
    public static final String TABLE1_COL5 = "REMIND_AT";
    public static final String TABLE1_COL6 = "USER ID";
    public static final String TABLE2_NAME = "user_table";
    public static final String TABLE2_COL1 = "USER_ID";
    public static final String TABLE2_COL2 = "NAME";
    public static final String TABLE2_COL3 = "USERNAME";
    public static final String TABLE2_COL4 = "PHONE";
    public static final String TABLE2_COL5 = "EMAIL";
    public static final String TABLE2_COL6 = "PASSWORD";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1_NAME + "(TABLE1_COL1 INTEGER PRIMARY KEY AUTOINCREMENT, TABLE1_COL2 TEXT, TABLE1_COL3 TEXT, TABLE1_COL4 DATE, TABLE1_COL5 DATE, TABLE1_COL6 INTEGER NOT NULL, FOREIGN KEY("+TABLE2_COL1+") REFERENCES "+TABLE2_NAME+"("+TABLE2_COL1+"))");
        db.execSQL("create table " + TABLE2_NAME + "(TABLE2_COL1 INTEGER PRIMARY KEY AUTOINCREMENT, TABLE2_COL2 TEXT, TABLE3_COL3 TEXT, TABLE2_COL4 INTEGER, TABLE2_COL5 TEXT, TABLE2_COL6 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE1_NAME);
        db.execSQL("drop table if exists " + TABLE2_NAME);
        onCreate(db);
    }
}
