package com.example.ellyn.assignment;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    MaterialEditText updateEditName, updateEditUsername, updateEditPhone, updateEditPassword;
    private Button btnSave;
    private FirebaseAuth auth;
    private DatabaseReference databaseUser;
    private String currentUserID;
    private FirebaseAuth mFireAuth;
    private DatabaseReference editProfileUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        auth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference("users");
        mFireAuth = FirebaseAuth.getInstance();
        currentUserID = mFireAuth.getCurrentUser().getUid();
        editProfileUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserID);

        updateEditName = (MaterialEditText) findViewById(R.id.updateName);
        updateEditUsername = (MaterialEditText) findViewById(R.id.updateUsername);
        updateEditPhone = (MaterialEditText) findViewById(R.id.updatePhone);
        updateEditPassword = (MaterialEditText) findViewById(R.id.updatePassword);
        btnSave = (Button) findViewById(R.id.save);



        editProfileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String myName = dataSnapshot.child("name").getValue().toString();
                    String myUserName = dataSnapshot.child("username").getValue().toString();
                    String myPhone = dataSnapshot.child("phone").getValue().toString();
                    String myEmail = dataSnapshot.child("email").getValue().toString();
                    String myPassword = dataSnapshot.child("password").getValue().toString();

                    updateEditName.setText(myName);
                    updateEditUsername.setText(myUserName);
                    updateEditPhone.setText(myPhone);
                    updateEditPassword.setText(myPassword);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUpdateProfileInfo();
                EditProfileActivity.this.finish();

            }
        });
    }

    private void validateUpdateProfileInfo() {
        String updatedName = updateEditName.getText().toString();
        String updatedUsername = updateEditUsername.getText().toString();
        String updatedPhone = updateEditPhone.getText().toString();
        String updatedPassword = updateEditPassword.getText().toString();

        if (TextUtils.isEmpty(updatedName)) {
            Toast.makeText(getApplicationContext(), "Enter full name!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(updatedUsername)) {
            Toast.makeText(getApplicationContext(), "Enter user ID!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(updatedPhone)) {
            Toast.makeText(getApplicationContext(), "Enter phoneno!", Toast.LENGTH_SHORT).show();
            return;
        }

        //else if (TextUtils.isEmpty(updatedPassword)) {
        //Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
        //return;
        //}
        //else if (updatedPassword.length() < 6) {
        //    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
        //    return;
        //}
        else {
            UpdateProfileInfo(updatedName, updatedUsername, updatedPhone, updatedPassword);
        }


    }


    private void UpdateProfileInfo(String updatedName, String updatedUsername, String updatedPhone, String updatedPassword) {
        HashMap userMap = new HashMap();
        userMap.put("name", updatedName);
        userMap.put("username", updatedUsername);
        userMap.put("phone", updatedPhone);
        userMap.put("password", updatedPassword);

        editProfileUserRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Error while updating profile!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
