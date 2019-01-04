package com.example.ellyn.assignment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReminderFragment extends Fragment {

    RecyclerView rRecyclerView;
    FirebaseDatabase rFirebaseDatabase;
    DatabaseReference rDatabaseReference;
    LinearLayoutManager reminderLayoutManager;
    FirebaseUser user;
    String userID;
    Calendar c;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return inflater.inflate(R.layout.fragment_reminder, container, false);
        }
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        FloatingActionButton reminderFloatingActionButton = view.findViewById(R.id.floatingButton);
        reminderFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new Reminder2Fragment()).commit();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        rRecyclerView = view.findViewById(R.id.reminderRecyclerView);
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        rFirebaseDatabase = FirebaseDatabase.getInstance();
        rDatabaseReference = rFirebaseDatabase.getReference("reminder");

        return view;
        }


    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<ReminderList, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ReminderList, ViewHolder>(ReminderList.class, R.layout.cardview, ViewHolder.class, rDatabaseReference) {
            @Override
            protected void populateViewHolder(final ViewHolder viewHolder, ReminderList reminderList, int position) {

                if (userID.equals(reminderList.getUserID())) {
                    viewHolder.setDetails(getActivity().getApplicationContext(), reminderList.getFoodName(), reminderList.getFoodCategory(), reminderList.getExpiryDate());
                }

            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String rName = getItem(position).getFoodName();
                        String rCategory = getItem(position).getFoodCategory();
                        String rExpiryDate = getItem(position).getExpiryDate();
                        String rRemindAt = getItem(position).getRemindAt();
                        String rRemindDate = getItem(position).getRemindDate();

                        Intent intent = new Intent(view.getContext(), ReminderDetailsActivity.class);
                        intent.putExtra("name", rName);
                        intent.putExtra("category", rCategory);
                        intent.putExtra("expirydate", rExpiryDate);
                        intent.putExtra("remindat", rRemindAt);
                        intent.putExtra("reminddate", rRemindDate);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        String currentID = getItem(position).getReminderID();
                        String currentName = getItem(position).getFoodName();
                        String currentCategory = getItem(position).getFoodCategory();
                        String currentExpiryDate = getItem(position).getExpiryDate();
                        String currentRemindAt = getItem(position).getRemindAt();
                        showDeleteDataDialog(currentName, currentCategory, currentExpiryDate, currentRemindAt, currentID);
                    }
                });

                return viewHolder;
            }

        };

        rRecyclerView.setAdapter(firebaseRecyclerAdapter);

        reminderLayoutManager = new LinearLayoutManager(getActivity());
        reminderLayoutManager.setReverseLayout(true);
        reminderLayoutManager.setStackFromEnd(true);

        rRecyclerView.setLayoutManager(reminderLayoutManager);
        rRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showDeleteDataDialog(final String currentName, String currentCategory, String currentExpiryDate, String currentRemindAt, final String currentID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete this reminder?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Query rQuery = rDatabaseReference.orderByChild("reminderID").equalTo(currentID);
                rQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(getActivity(), "Reminder deleted successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

}
