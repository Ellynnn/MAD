package com.example.ellyn.assignment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Reminder2Fragment extends Fragment {

    DatabaseReference databaseReminder;
    FirebaseUser user;

    Button selectExpiryDate;
    TextView expiryDate;
    DatePickerDialog datePickerDialog;
    Spinner categorySpinner;
    Spinner remindAtSpinner;
    Button clearButton;
    EditText foodName;
    Button confirmButton;
    String userID;
    Date foodExpiryDate;
    Date remindDate;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container==null) {
            return inflater.inflate(R.layout.fragment_reminder2, container, false);
        }
        View view = inflater.inflate(R.layout.fragment_reminder2,container,false);

        Button cancelButton = view.findViewById(R.id.reminder2cancelbutton);
        clearButton = view.findViewById(R.id.reminder2clearbutton);
        confirmButton = view.findViewById(R.id.reminder2confirmbutton);
        remindAtSpinner = view.findViewById(R.id.reminder2remindatspinner);
        categorySpinner = view.findViewById(R.id.reminder2categoryspinner);
        foodName = view.findViewById(R.id.reminder2entername);
        selectExpiryDate = view.findViewById(R.id.reminder2expirydatebutton);
        expiryDate = view.findViewById(R.id.reminder2selectexpirydate);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReminderFragment()).commit();
            }
        });

        databaseReminder = FirebaseDatabase.getInstance().getReference("reminder");

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
                        if(remindAtSpinner.getSelectedItem().equals("1 week before")) {
                            remindDate = addDays(c.getTime(), -7);
                        }
                        else if(remindAtSpinner.getSelectedItem().equals("2 weeks before")) {
                            remindDate = addDays(c.getTime(), -14);
                        }
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                        expiryDate.setText(date);


                    }
                }, c_year, c_month, c_day);
                datePickerDialog.show();
            }
        });


        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Choose a category");
        categoryList.add("Frozen Food");
        categoryList.add("Can Food");
        categoryList.add("Snacks");
        categoryList.add("Beverage");
        categoryList.add("Others");


        ArrayList<String> remindAtList = new ArrayList<>();
        remindAtList.add("Choose reminder time");
        remindAtList.add("1 week before");
        remindAtList.add("2 weeks before");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, categoryList);
        categorySpinner.setAdapter(categoryAdapter);

        final ArrayAdapter<String> remindAtAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, remindAtList);
        remindAtSpinner.setAdapter(remindAtAdapter);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expiryDate.setText(R.string.select_expiry_date);
                foodName.getText().clear();
                categorySpinner.setSelection(0);
                remindAtSpinner.setSelection(0);
            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodName.getText().toString().matches("") || remindAtSpinner.getSelectedItemPosition() == 0 || categorySpinner.getSelectedItemPosition() == 0 || expiryDate.toString().equals(NULL)){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                    alertBuilder.setMessage("Please fill in the blanks!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.setTitle("Error!");
                    alert.show();
                }
                else {
                    addReminder();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReminderFragment()).commit();
                }
                }
        });

        return view;

    }
    private void addReminder(){
        String foodCategory = categorySpinner.getSelectedItem().toString();
        String name = foodName.getText().toString().trim();
        String expiry_date = expiryDate.getText().toString();
        String remind_at = remindAtSpinner.getSelectedItem().toString();
        String remind_date = new SimpleDateFormat("dd/MM/yyyy").format(remindDate);
        String id = databaseReminder.push().getKey();
        String user_id = user.getUid();
        Reminder reminder = new Reminder(id, foodCategory, name, expiry_date, remind_at, remind_date, user_id);

        databaseReminder.child(id).setValue(reminder);
    }

    public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

}

