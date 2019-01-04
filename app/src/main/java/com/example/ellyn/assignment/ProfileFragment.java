package com.example.ellyn.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    TextView name, username, phone, email;
    Button editProfile;
    Button signOut;
    String currentUserID;
    FirebaseAuth mFireAuth;
    DatabaseReference profileUserRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mFireAuth = FirebaseAuth.getInstance();
        currentUserID = mFireAuth.getCurrentUser().getUid();
        profileUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserID);

        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);

        profileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String myName = dataSnapshot.child("name").getValue().toString();
                    String myUserName = dataSnapshot.child("username").getValue().toString();
                    String myPhone = dataSnapshot.child("phone").getValue().toString();
                    String myEmail = dataSnapshot.child("email").getValue().toString();
                    String myPassword = dataSnapshot.child("password").getValue().toString();

                    name.setText(myName) ;
                    username.setText(myUserName);
                    phone.setText(myPhone);
                    email.setText(myEmail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        editProfile = view.findViewById(R.id.btnEdit);
        editProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        signOut = view.findViewById(R.id.btnSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
