package com.example.ellyn.assignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment {

    View myView;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if(container==null) {
            return inflater.inflate(R.layout.fragment_home, container, false);
        }

        myView = inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper = myView.findViewById(R.id.slideshowViewFlipper);

        fade_in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);

        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();

        return myView;

    }
}