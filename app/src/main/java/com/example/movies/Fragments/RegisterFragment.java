package com.example.movies.Fragments;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.example.movies.MainActivity;
import com.example.movies.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RegisterFragment extends Fragment {
    Button bt_register;
    private BottomNavigationView mMainNav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
//        Context context=getActivity();
//        mMainNav=((MainActivity)context).findViewById(R.id.main_nav);
//        mMainNav.setVisibility(View.GONE);

        bt_register=v.findViewById(R.id.bt_regi);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new ContainerFragment());
                fragmentTransaction.replace(R.id.container, new FirstPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }


}
