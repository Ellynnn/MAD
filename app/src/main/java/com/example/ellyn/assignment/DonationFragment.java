package com.example.ellyn.assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class DonationFragment extends Fragment {

    private Button takePictureButton;
    private ImageView imageView;
    Uri file;
    private Spinner categorySpinner;
    private Button clearButton;
    private EditText foodName;
    private EditText foodQty;
    private Button confirmButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if(container==null) {
        return inflater.inflate(R.layout.fragment_donation, container, false);}

        View view =   inflater.inflate(R.layout.fragment_donation,container,false);

        categorySpinner = view.findViewById(R.id.donation_category_spinner);
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Choose a category");
        categoryList.add("Frozen Food");
        categoryList.add("Can Food");
        categoryList.add("Snacks");
        categoryList.add("Beverage");
        categoryList.add("Others");

        final ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, categoryList);
        categorySpinner.setAdapter(categoryAdapter);

        takePictureButton = view.findViewById(R.id.btnCamera);
        imageView = view.findViewById(R.id.imageView);

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        foodName = view.findViewById(R.id.enter_food_donation_name);
        foodQty =  view.findViewById(R.id.enter_quantity);

        clearButton = view.findViewById(R.id.donation_clear_btn);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodName.getText().clear();
                foodQty.getText().clear();
                categorySpinner.setSelection(0);
                imageView.setImageBitmap(null);
            }
        });

        confirmButton = view.findViewById(R.id.donation_confirm_btn);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (foodName.getText().toString().matches("") || categorySpinner.getSelectedItemPosition() == 0 || foodQty.getText().toString().matches("") ){
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

                else{
                    final Toast toast = Toast.makeText( getActivity(), "Thank you for your donation!! We have receive your request. " +
                            "Please send it to the nearby centre.", Toast.LENGTH_LONG);
                    toast.show();

                    foodName.getText().clear();
                    foodQty.getText().clear();
                    categorySpinner.setSelection(0);
                    imageView.setImageBitmap(null);
                    }
            }});

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }

}
