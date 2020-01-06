package com.example.movies.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.movies.DatabaseHelper;
import com.example.movies.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RegisterFragment extends Fragment {
    private Context context;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        final EditText  name1=v.findViewById(R.id.et_name);
        final EditText  password1=v.findViewById(R.id.et_password);
        final EditText  passwordw=v.findViewById(R.id.et_passwordw);
        final Button bt_register=v.findViewById(R.id.bt_regi);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name1.getText().toString();
                if(userName.isEmpty()){
                    name1.setError("Please enter a username");
                    return;
                }

                String password = password1.getText().toString();
                if(password.isEmpty()){
                    password1.setError("Please enter a password");
                    return;
                }

                String wpassword = passwordw.getText().toString();
                if(wpassword.isEmpty()){
                    passwordw.setError("Please repeat password");
                    return;
                }

                if(!password.equals(wpassword)){
                    passwordw.setError("Does not match previous password");
                    return;
                }

                DatabaseHelper db = new DatabaseHelper(context);
                if(!db.insertUser(userName,password)){
                    Toast.makeText(context, "Cannot insert new user", Toast.LENGTH_LONG).show();
                    return;
                }

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
