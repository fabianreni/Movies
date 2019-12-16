package com.example.movies.Fragments;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.movies.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ContainerFragment extends Fragment {
    private BottomNavigationView mMainNav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_container, container, false);
        mMainNav=(BottomNavigationView)v.findViewById(R.id.main_nav);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        FragmentManager fm=getFragmentManager();
                        fm.beginTransaction().replace(R.id.container,new FirstPageFragment()).commit();
                        return true;
                    case R.id.nav_prof:
                        fm=getFragmentManager();
                        fm.beginTransaction().replace(R.id.container,new ProfilFragment()).commit();
                        return true;
                    case R.id.nav_favorite:
                        fm=getFragmentManager();
                        fm.beginTransaction().replace(R.id.container,new FavoritesFragment()).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });
        //if keyboard is showing set bottomNavigationBar's visibility to GONE
        final ConstraintLayout constraintLayout=v.findViewById(R.id.main2);
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new   ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                constraintLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = constraintLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) {
                    //Toast.makeText(MainActivity.this,"Keyboard is showing",Toast.LENGTH_LONG).show();
                    mMainNav.setVisibility(View.GONE);
                } else {
                    //Toast.makeText(MainActivity.this,"keyboard closed",Toast.LENGTH_LONG).show();
                    mMainNav.setVisibility(View.VISIBLE);
                }
            }
        });

        return v;
    }

}
