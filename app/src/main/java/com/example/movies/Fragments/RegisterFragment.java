package com.example.movies.Fragments;

import android.content.ContentValues;
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
    Button bt_register;
    EditText name1,password1,email1;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private BottomNavigationView mMainNav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register, container, false);
        name1=v.findViewById(R.id.et_name);
        email1= v.findViewById(R.id.et_email);
        password1=v.findViewById(R.id.et_password);
        openHelper=new DatabaseHelper(getActivity());
        bt_register=v.findViewById(R.id.bt_regi);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fname=name1.getText().toString();
                String femail=email1.getText().toString();
                String fpassword=password1.getText().toString();
                insertdata(fname,femail,fpassword);
                Toast.makeText(getActivity(),"Register successfully",Toast.LENGTH_LONG).show();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new ContainerFragment());
                fragmentTransaction.replace(R.id.container, new FirstPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }
    public  void  insertdata(String names,String email,String pass){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,names);
        contentValues.put(DatabaseHelper.COL_3,email);
        contentValues.put(DatabaseHelper.COL_4,pass);
        long id =db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }


}
