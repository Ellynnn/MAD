package com.example.ellyn.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class login extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnLogin,btnLinkRegister;
    private FirebaseDatabase firebaseDatabase;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view now
        setContentView(R.layout.activity_login);

        //Get Firebase  instance
        // firebaseDatabase = FirebaseDatabase.getInstance();

        // if (firebaseDatabase.getCurrentUser() != null) {
        // startActivity(new Intent(login.this, MainActivity.class));
        //  finish();
        //}



        // Locate the button in activity_login.xml
        inputEmail = (EditText) findViewById(R.id.EditEmail);
        inputPassword = (EditText) findViewById(R.id.EditPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLinkRegister = (Button) findViewById(R.id.btn_link_register);

        // Capture register button click
        btnLinkRegister.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start login.class
                Intent myIntent = new Intent(login.this, Registration.class);
                startActivity(myIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                //firebaseDatabase.signInWithEmailAndPassword(email, password)
                //   .addOnCompleteListener(login.this, new OnCompleteListener<DatabaseReference>() {
                //    @Override
                //  public void onComplete(@NonNull Task<DatabaseReference> task) {
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                // progressBar.setVisibility(View.GONE);
                //if (!task.isSuccessful()) {
                // there was an error
                //  if (password.length() < 6) {
                //    inputPassword.setError(getString(R.string.minimum_password));
                //} else {
                //    Toast.makeText(login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                //}
                //} else {
                //  Intent intent = new Intent(login .this, MainActivity.class);
                //startActivity(intent);
                //finish();
                // }
                //}
                //});
            }
        });
    }
}