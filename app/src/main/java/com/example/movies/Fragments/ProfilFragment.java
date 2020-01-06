package com.example.movies.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movies.MainActivity;
import com.example.movies.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.app.Activity.RESULT_OK;

public class ProfilFragment extends Fragment {

    Button bt_setpic;

    public static final int PICK_IMAGE = 1;
    ImageView profilpik;
    SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profil, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String names= preferences.getString(getString(R.string.NAME), "defaultValue");
        String password=preferences.getString(getString(R.string.PASS), "defaultValue");
        //set name
        TextView name1,p;
        name1=v.findViewById(R.id.tv_name);
        name1.setText(names);
        p=v.findViewById(R.id.tv_password);
        p.setText(password);

        profilpik=v.findViewById(R.id.profilImage);

        bt_setpic=v.findViewById(R.id.bt_setProfilpic);
        bt_setpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent togallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(togallery,PICK_IMAGE);
            }
        });


        return v;
  }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE)
        {

            Uri image = data.getData();
            profilpik.setImageURI(image);
        }
    }
}
