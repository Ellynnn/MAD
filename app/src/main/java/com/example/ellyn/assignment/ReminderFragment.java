package com.example.ellyn.assignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ReminderFragment extends Fragment {

    RecyclerView rRecyclerView;
    FirebaseDatabase rFirebaseDatabase;
    DatabaseReference rDatabaseReference;
    LinearLayoutManager reminderLayoutManager;

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

        rRecyclerView = view.findViewById(R.id.reminderRecyclerView);
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        rFirebaseDatabase = FirebaseDatabase.getInstance();
        rDatabaseReference = rFirebaseDatabase.getReference("reminder");

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<ReminderList, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ReminderList, ViewHolder>(ReminderList.class, R.layout.cardview, ViewHolder.class, rDatabaseReference) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, ReminderList reminderList, int position) {

                viewHolder.setDetails(getActivity().getApplicationContext(), reminderList.getFoodName(), reminderList.getFoodCategory(), reminderList.getExpiryDate());

            }
        };

        rRecyclerView.setAdapter(firebaseRecyclerAdapter);

        reminderLayoutManager = new LinearLayoutManager(getActivity());
        reminderLayoutManager.setReverseLayout(true);
        reminderLayoutManager.setStackFromEnd(true);

        rRecyclerView.setLayoutManager(reminderLayoutManager);
        rRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}
