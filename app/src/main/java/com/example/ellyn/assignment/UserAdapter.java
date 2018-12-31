package com.example.ellyn.assignment;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class UserAdapter {

    private Context mContext;
    private List<User> mUsers;

    private FirebaseUser firebaseUser;

    public UserAdapter(Context mContext, List<User> mUsers){
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    //@NonNull
    //@Override
    //public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
    //    View view = LayoutInflater.from(mContext).inflate(R.layout)
    //}
}
