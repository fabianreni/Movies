package com.example.movies.Fragments;

import android.content.Context;
import android.database.Cursor;
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

public class LoginFragment extends Fragment {
    Button bt_regist,bt_log;
    EditText name,pass;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context=getActivity();
        View v=inflater.inflate(R.layout.fragment_login, container, false);

        name=v.findViewById(R.id.et_name1);
        pass=v.findViewById(R.id.et_password);

        openHelper=new DatabaseHelper(getActivity());
        db=openHelper.getReadableDatabase();


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
                cursor=db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " +DatabaseHelper.COL_2 + " =? AND " + DatabaseHelper.COL_4 +" =? ", new String[]{names,password});
                if(cursor!=null){
                    if (cursor.getCount()>0){
                        Toast.makeText(getActivity(),"Login successfully",Toast.LENGTH_LONG).show();
                        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainActivity, new ContainerFragment());
                        fragmentTransaction.replace(R.id.container, new FirstPageFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }else {
                            Toast.makeText(getActivity(),"Username or password is wrong",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        return v;
    }


}
