package com.example.movies.Fragments;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.movies.MainActivity;
import com.example.movies.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilFragment extends Fragment {
    private BottomNavigationView mMainNav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profil, container, false);

        //if keyboard is showing set bottomNavigationBar's visibility to GONE
        //if keyboard is showing set bottomNavigationBar's visibility to GONE
//        Context context=getActivity();
//        mMainNav=((MainActivity)context).findViewById(R.id.main_nav);
//        mMainNav.setVisibility(View.VISIBLE);

        return v;
  }

}
