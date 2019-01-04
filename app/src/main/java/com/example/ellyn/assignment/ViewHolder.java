package com.example.ellyn.assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ViewHolder extends RecyclerView.ViewHolder {

    private View rview;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        rview = itemView;

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                rClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                rClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    public void setDetails(Context context, String name, String category, String expirydate){
        TextView rFoodName = rview.findViewById(R.id.foodName);
        TextView rFoodCategory = rview.findViewById(R.id.foodCategory);
        TextView rFoodExpiryDate = rview.findViewById(R.id.foodExpiryDate);

        rFoodName.setText(name);
        rFoodCategory.setText(category);
        rFoodExpiryDate.setText(expirydate);
    }

    private ViewHolder.ClickListener rClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        rClickListener = clickListener;
    }


}
