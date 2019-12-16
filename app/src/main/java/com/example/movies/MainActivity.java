package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.movies.Fragments.FavoritesFragment;
import com.example.movies.Fragments.FirstPageFragment;
import com.example.movies.Fragments.LoginFragment;
import com.example.movies.Fragments.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.jar.JarEntry;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set fragment
        if (savedInstanceState == null)
        {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainActivity, new LoginFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
}
