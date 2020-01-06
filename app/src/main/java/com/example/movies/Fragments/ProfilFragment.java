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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.DatabaseHelper;
import com.example.movies.MainActivity;
import com.example.movies.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.app.Activity.RESULT_OK;

public class ProfilFragment extends Fragment {

    private Button bt_setpic;
    private  ImageButton bt_save,bt_change,bt_changep,bt_savep;
    public static final int PICK_IMAGE = 1;
    private  ImageView profilpik;
    private Context context;
    private  SharedPreferences preferences;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        ((MainActivity) context).setTitle("Profile");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profil, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String names= preferences.getString(getString(R.string.NAME), "defaultValue");
        final String password=preferences.getString(getString(R.string.PASS), "defaultValue");
       bt_change = v.findViewById(R.id.bt_changeName);
       bt_save = v.findViewById(R.id.bt_save);
        bt_changep = v.findViewById(R.id.bt_changep);
        bt_savep = v.findViewById(R.id.bt_savep);

        //set name
        final EditText name,pass;
        name=v.findViewById(R.id.et_name);
        name.setText(names);
        pass=v.findViewById(R.id.et_password);
       // pass.setText(password);


        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setFocusableInTouchMode(true);
                name.setFocusable(true);
                name.setText("");
                name.requestFocus();

                bt_save.setVisibility(View.VISIBLE);
                bt_change.setVisibility(View.GONE);
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = name.getText().toString();
                if(newUsername.isEmpty()){
                    name.setError("Please enter a username");
                    return;
                }

                DatabaseHelper db = new DatabaseHelper(context);
                if(!db.changeUsername(names, newUsername)){
                    Toast.makeText(context, "Cannot update username", Toast.LENGTH_LONG).show();
                    return;
                }

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(getString(R.string.NAME), newUsername);
                editor.apply();

                name.setFocusable(false);
                name.setFocusableInTouchMode(false);
                bt_save.setVisibility(View.GONE);
                bt_change.setVisibility(View.VISIBLE);
            }
        });

        bt_changep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass.setVisibility(View.VISIBLE);
                pass.setFocusableInTouchMode(true);
                pass.setFocusable(true);
                pass.setText("");
                pass.requestFocus();

                bt_savep.setVisibility(View.VISIBLE);
                bt_changep.setVisibility(View.GONE);
            }
        });

        bt_savep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = pass.getText().toString();
                if(newPass.isEmpty()){
                    pass.setError("Please enter a pasword");
                    return;
                }

                DatabaseHelper db = new DatabaseHelper(context);
                if(!db.changePassword(password, newPass)){
                    Toast.makeText(context, "Cannot update password", Toast.LENGTH_LONG).show();
                    return;
                }

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(getString(R.string.PASS), newPass);
                editor.apply();
                pass.setVisibility(View.GONE);
                pass.setFocusable(false);
                pass.setFocusableInTouchMode(false);
                bt_savep.setVisibility(View.GONE);
                bt_changep.setVisibility(View.VISIBLE);
            }
        });


        //set profil pik
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
