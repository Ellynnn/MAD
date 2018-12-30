package com.example.ellyn.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Registration extends AppCompatActivity {
    private EditText inputrfullname, inputruserid, inputrphoneno , inputremail, inputrpassword;
    private Button  btnregister, btnlinklogin;
    private ProgressBar progressBar;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Get Firebase auth instance
        firebaseDatabase = FirebaseDatabase.getInstance();

        btnregister = (Button) findViewById(R.id.btn_register);
        inputrfullname = (EditText) findViewById(R.id.rEditname);
        inputruserid = (EditText) findViewById(R.id.rEdituserid);
        inputrphoneno = (EditText) findViewById(R.id.rEditphoneno);
        inputremail = (EditText) findViewById(R.id.rEditEmail);
        inputrpassword = (EditText) findViewById(R.id.rEditPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnlinklogin = (Button) findViewById(R.id.btn_link_login);


        btnlinklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Registration.this, login.class);
                startActivity(myIntent);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname = inputrfullname.getText().toString().trim();
                String userid = inputruserid.getText().toString().trim();
                String phoneno = inputrphoneno.getText().toString().trim();
                String email = inputremail.getText().toString().trim();
                String password = inputrpassword.getText().toString().trim();

                if (TextUtils.isEmpty(fullname)) {
                    Toast.makeText(getApplicationContext(), "Enter full name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userid)) {
                    Toast.makeText(getApplicationContext(), "Enter user ID!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phoneno)) {
                    Toast.makeText(getApplicationContext(), "Enter phoneno!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}