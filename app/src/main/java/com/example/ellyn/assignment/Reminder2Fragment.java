package com.example.ellyn.assignment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Reminder2Fragment extends Fragment {

    Button selectExpiryDate;
    TextView expiryDate;
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container==null) {
            return inflater.inflate(R.layout.fragment_reminder2, container, false);
        }
        View view =   inflater.inflate(R.layout.fragment_reminder2,container,false);
        Button cancelButton = view.findViewById(R.id.reminder2cancelbutton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReminderFragment()).commit();
            }
        });

        selectExpiryDate = view.findViewById(R.id.reminder2expirydatebutton);
        expiryDate = view.findViewById(R.id.reminder2selectexpirydate);

        selectExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        expiryDate.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
            }
        });

        return view;

    }

}

