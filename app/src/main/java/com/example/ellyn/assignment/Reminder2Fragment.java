package com.example.ellyn.assignment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Reminder2Fragment extends Fragment {

    Button selectExpiryDate;
    TextView expiryDate;
    DatePickerDialog datePickerDialog;
    Spinner categorySpinner;
    Spinner remindAtSpinner;
    Button clearButton;
    EditText foodName;

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
                final Calendar c = Calendar.getInstance();
                int c_year = c.get(Calendar.YEAR);
                int c_month = c.get(Calendar.MONTH);
                final int c_day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        c.set(year, month, dayOfMonth);
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                        expiryDate.setText(date);
                        //expiryDate.setText(c_day + "/" + (c_month + 1 ) + "/" + c_year);
                    }
                }, c_year, c_month, c_day);
                datePickerDialog.show();
            }
        });

        categorySpinner = view.findViewById(R.id.reminder2categoryspinner);
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Choose a category");
        categoryList.add("Frozen Food");
        categoryList.add("Can Food");
        categoryList.add("Snacks");
        categoryList.add("Beverage");
        categoryList.add("Others");

        remindAtSpinner = view.findViewById(R.id.reminder2remindatspinner);
        ArrayList<String> remindAtList = new ArrayList<>();
        remindAtList.add("Choose reminder time");
        remindAtList.add("1 week before");
        remindAtList.add("2 weeks before");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, categoryList);
        categorySpinner.setAdapter(categoryAdapter);

        final ArrayAdapter<String> remindAtAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, remindAtList);
        remindAtSpinner.setAdapter(remindAtAdapter);


        foodName = view.findViewById(R.id.reminder2entername);

        clearButton = view.findViewById(R.id.reminder2clearbutton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expiryDate.setText(R.string.select_expiry_date);
                foodName.setText(R.string.food_name_hint);
                categorySpinner.setSelection(0);
                remindAtSpinner.setSelection(0);
            }
        });

        return view;

    }

}

