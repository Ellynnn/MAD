package com.example.ellyn.assignment;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReminderFragment extends Fragment {

    private RecyclerView reminderList;
    private DatabaseReference databaseReminder;
    private LinearLayoutManager reminderLayoutManager;

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

                getFragmentManager().beginTransaction().add(R.id.fragment_container, new Reminder2Fragment()).commit();

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Reminder2Fragment()).commit();
            }
        });

        databaseReminder = FirebaseDatabase.getInstance().getReference().child("reminder");
        databaseReminder.keepSynced(true);

        reminderList = view.findViewById(R.id.reminderRecyclerView);
        reminderList.setHasFixedSize(true);
        reminderList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<ReminderList,ReminderViewHolder>FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ReminderList, ReminderViewHolder>(ReminderList.class, R.layout.cardview, ReminderViewHolder.class, databaseReminder) {
            @Override
            protected void populateViewHolder(ReminderViewHolder viewHolder, ReminderList model, int position) {
                viewHolder.setFoodName(model.getFoodName());
                viewHolder.setFoodCategory(model.getFoodCategory());
                viewHolder.setExpiryDate(model.getExpiryDate());
            }
        };

        reminderList.setAdapter(FirebaseRecyclerAdapter);

        reminderLayoutManager = new LinearLayoutManager(getActivity());
        reminderLayoutManager.setReverseLayout(true);
        reminderLayoutManager.setStackFromEnd(true);

        reminderList.setLayoutManager(reminderLayoutManager);
        reminderList.setAdapter(FirebaseRecyclerAdapter);

    }

    public static class ReminderViewHolder extends RecyclerView.ViewHolder{
        View rView;
        public ReminderViewHolder(View itemView){
            super(itemView);
            rView = itemView;
        }

        public void setFoodName(String name){
            TextView foodName = rView.findViewById(R.id.foodName);
            foodName.setText(name);
        }
        public void setFoodCategory(String category){
            TextView foodCategory = rView.findViewById(R.id.foodCategory);
            foodCategory.setText(category);
        }
        public void setExpiryDate(String expiryDate){
            TextView foodExpiryDate = rView.findViewById(R.id.foodExpiryDate);
            foodExpiryDate.setText(expiryDate);
        }
    }

}
