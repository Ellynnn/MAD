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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    MaterialEditText updateEditName, updateEditUsername, updateEditPhone, currentPassword, updateEditPassword;
    private Button btnSave, btnClose;
    private FirebaseAuth auth;
    private DatabaseReference databaseUser;
    private String currentUserID, userCurrentEmail, userCurrentPwd;
    private DatabaseReference editProfileUserRef;
    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        auth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference("users");
        currentUserID = auth.getCurrentUser().getUid();
        editProfileUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserID);

        updateEditName = (MaterialEditText) findViewById(R.id.updateName);
        updateEditUsername = (MaterialEditText) findViewById(R.id.updateUsername);
        updateEditPhone = (MaterialEditText) findViewById(R.id.updatePhone);
        currentPassword = (MaterialEditText) findViewById(R.id.currentPassword);
        updateEditPassword = (MaterialEditText) findViewById(R.id.updatePassword);
        btnSave = (Button) findViewById(R.id.save);
        btnClose = (Button) findViewById(R.id.close);

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
                    userCurrentEmail = myEmail;
                    userCurrentPwd = myPassword;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateUpdateProfileInfo()){
                    EditProfileActivity.this.finish();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileActivity.this.finish();
            }
        });
    }

    private boolean validateUpdateProfileInfo() {
        String updatedName = updateEditName.getText().toString();
        String updatedUsername = updateEditUsername.getText().toString();
        String updatedPhone = updateEditPhone.getText().toString();
        String updatedPassword = updateEditPassword.getText().toString().trim();
        String currentPwd = currentPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(currentPwd)) {
            if (!TextUtils.isEmpty(updatedPassword)) {
                auth.signInWithEmailAndPassword(userCurrentEmail, currentPassword.getText().toString())
                        .addOnCompleteListener(EditProfileActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(EditProfileActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    result = false;
                                } else {
                                    FirebaseUser user = auth.getCurrentUser();
                                    if (user != null)
                                    {
                                        user.updatePassword(updateEditPassword.getText().toString())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            UpdateProfileInfo(updateEditName.getText().toString(), updateEditUsername.getText().toString(), updateEditPhone.getText().toString(), updateEditPassword.getText().toString());
                                                            EditProfileActivity.this.finish();
                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Update Failed !", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                }
                            }
                        });
            } else {
                updateEditPassword.setError("Please Enter New Password !");
                result = false;}
        } else {
            result = true;}

        if (TextUtils.isEmpty(updatedName)) {
            updateEditName.setError("Please Enter Your Name !");
            result = false;
        } if (TextUtils.isEmpty(updatedUsername)) {
            updateEditUsername.setError("Please Enter Your Username !");
            result = false ;
        } if (TextUtils.isEmpty(updatedPhone)) {
            updateEditPhone.setError("Please Enter Phone Number!");
            result = false;
        } if (updatedPhone.length()<10 || updatedPhone.length()>11) {
            Toast.makeText(getApplicationContext(), "Enter valid phone number!", Toast.LENGTH_SHORT).show();
            result = false;
        }

        if (result){
            UpdateProfileInfo(updatedName, updatedUsername, updatedPhone, userCurrentPwd); }
        return result;
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
                    Toast.makeText(EditProfileActivity.this, "Profile is successfully updated !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Error while updating profile !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
