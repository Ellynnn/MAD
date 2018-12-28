package com.example.ellyn.assignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Reminder2Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //if(container==null) {
            return inflater.inflate(R.layout.fragment_reminder2, container, false);
        //}
        //View view =   inflater.inflate(R.layout.fragment_reminder2,container,false);
        //Button cancelButton = view.findViewById(R.id.reminder2cancelbutton);
        //cancelButton.setOnClickListener(new View.OnClickListener(){
        //    @Override
        //    public void onClick(View v){
        //        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReminderFragment()).commit();
       //     }
       // });
        //return view;


    }
}

