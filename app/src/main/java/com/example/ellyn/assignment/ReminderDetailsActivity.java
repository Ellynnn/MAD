package com.example.ellyn.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ReminderDetailsActivity extends AppCompatActivity {

    TextView rdName, rdCategory, rdExpiryDate, rdRemindAt;
    Button btnCancel;
    DatabaseReference rDatabaseReference;
    FirebaseDatabase rFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);

        rdName = findViewById(R.id.foodName);
        rdCategory = findViewById(R.id.foodCategory);
        rdExpiryDate = findViewById(R.id.foodExpiryDate);
        rdRemindAt = findViewById(R.id.foodRemindAt);
        btnCancel = findViewById(R.id.cancelButton);
        rFirebaseDatabase = FirebaseDatabase.getInstance();
        rDatabaseReference = rFirebaseDatabase.getReference("reminder");

        String name = getIntent().getStringExtra("name");
        String category = getIntent().getStringExtra("category");
        String expiry_date = getIntent().getStringExtra("expirydate");
        String remind_at = getIntent().getStringExtra("remindat");

        rdName.setText(name);
        rdCategory.setText(category);
        rdExpiryDate.setText(expiry_date);
        rdRemindAt.setText(remind_at);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderDetailsActivity.this.finish();
            }
        });


    }
}
