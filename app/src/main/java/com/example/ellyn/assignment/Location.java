package com.example.ellyn.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Location extends AppCompatActivity {

    private Button btnLocationBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        btnLocationBack = (Button) findViewById(R.id.btn_locationback);
        btnLocationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location.this.finish();
            }
        });
    }

}
