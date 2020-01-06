package com.example.movies.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class LoginFragment extends Fragment {
    Button bt_regist,bt_log;
    EditText name,pass;
    Context context;
    SharedPreferences sharedpreferences;
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login, container, false);

        name=v.findViewById(R.id.et_name1);
        pass=v.findViewById(R.id.et_password);

        sharedpreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());


        bt_regist=v.findViewById(R.id.bt_register);
        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new RegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        bt_log=v.findViewById(R.id.bt_login);
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String names=name.getText().toString();
                String password=pass.getText().toString();
                DatabaseHelper db = new DatabaseHelper(context);
                if(!db.userExists(names)){
                    name.setError("This username does not exist");
                    return;
                }
                if(!password.equals(db.getPassword(names))){
                    pass.setError("Incorrect password");
                    return;
                }
                SharedPreferences.Editor editor=sharedpreferences.edit();
                editor.putString(getActivity().getString(R.string.NAME),names);
                editor.putString(getActivity().getString(R.string.PASS),password);
                editor.commit();

                Toast.makeText(getActivity(),"Login successfully",Toast.LENGTH_LONG).show();

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
