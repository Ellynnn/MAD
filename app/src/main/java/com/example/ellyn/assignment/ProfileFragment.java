package com.example.ellyn.assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    TextView name, username, phone, email, pwd;
    Button editProfile;
    Button signOut;
    FirebaseUser firebaseUser;
    String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userID = prefs.getString("userID", "none");

        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        pwd = view.findViewById(R.id.pwd);

        userInfo();

        //editProfile.setOnClickListener(new View.OnClickListener(){
            //@Override
            //public void onClick(View view) {
                //String btn = editProfile.getText().toString();

                //if (btn.equals("Edit Profile")){

                //}
            //}
        //});
        signOut = view.findViewById(R.id.btnSignOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void userInfo(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (getContext() == null){
                    return;
                }

                //User user = dataSnapshot.getValue(User.class);

                //name.setText(user.);
                //username.setText(user.getUsername());
               // phone.setText(user.getPhone());
                //email.setText(user.getEmail());
                //pwd.setText(user.getPassword());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
