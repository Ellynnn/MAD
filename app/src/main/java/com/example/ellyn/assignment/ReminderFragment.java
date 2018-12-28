package com.example.ellyn.assignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReminderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if(container==null) {
            return inflater.inflate(R.layout.fragment_reminder, container, false);
        }
        View view =   inflater.inflate(R.layout.fragment_reminder,container,false);
        FloatingActionButton reminderFloatingActionButton = view.findViewById(R.id.floatingButton);
        reminderFloatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Reminder2Fragment()).commit();
            }
        });
        return view;
    }
}
