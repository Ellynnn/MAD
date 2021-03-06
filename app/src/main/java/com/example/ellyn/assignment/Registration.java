package com.example.ellyn.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.regex.Pattern;

@IgnoreExtraProperties
public class Registration extends AppCompatActivity {

    private EditText inputrfullname, inputrusername, inputrphoneno , inputremail, inputrpassword;
    private Button  btnregister, btnlinklogin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        btnregister = (Button) findViewById(R.id.btn_register);
        inputrfullname = (EditText) findViewById(R.id.rEditname);
        inputrusername = (EditText) findViewById(R.id.rEditusername);
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
                String username = inputrusername.getText().toString().trim();
                String phoneno = inputrphoneno.getText().toString().trim();
                String email = inputremail.getText().toString().trim();
                String password = inputrpassword.getText().toString().trim();

                if (TextUtils.isEmpty(fullname)) {
                    Toast.makeText(getApplicationContext(), "Enter full name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter user name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneno)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneno.length()<10 || phoneno.length()>11) {
                    Toast.makeText(getApplicationContext(), "Enter valid phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!email.contains("@")) {
                    Toast.makeText(getApplicationContext(), "Enter valid email address! (must include '@')", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!email.contains(".com"))  {
                    Toast.makeText(getApplicationContext(), "Enter valid email address! (must include '.com')", Toast.LENGTH_SHORT).show();
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
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Registration.this, "Account created!" , Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                addUser();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "Authentication failed!" , Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Registration.this, login.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    private void addUser(){

        String full_name = inputrfullname.getText().toString().trim();
        String user_name = inputrusername.getText().toString().trim();
        String phone_no = inputrphoneno.getText().toString().trim();
        String email = inputremail.getText().toString().trim();
        String password = inputrpassword.getText().toString().trim();
        String id = auth.getCurrentUser().getUid();
        User user = new User(id, full_name, user_name, phone_no, email, password);

        databaseUser.child(id).setValue(user);
    }
}