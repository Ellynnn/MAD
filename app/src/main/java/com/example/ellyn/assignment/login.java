package com.example.ellyn.assignment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_login.xml
        setContentView(R.layout.activity_login);
        // Locate the button in activity_login.xml
        btnLogin = (Button) findViewById(R.id.btn_login);

        // Capture login button clicks
        btnLogin.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start SecondActivity.class
                Intent myIntent = new Intent(login.this, SecondActivity.class);
                startActivity(myIntent);
            }
        });

    }
}


