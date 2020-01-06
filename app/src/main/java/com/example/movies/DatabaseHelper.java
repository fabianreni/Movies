package com.example.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.movies.api.ApiClient;
import com.example.movies.api.ApiService;
import com.example.movies.constant.Constant;
import com.example.movies.models.ImageResponse;
import com.example.movies.models.ImageResult;
import com.example.movies.models.Result;
import com.example.movies.models.VideoResponse;
import com.example.movies.models.VideoResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.net.Uri;


import androidx.annotation.Nullable;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MovieDB";

    private static final String TABLE_USERS = "Users";
    private static final String USERS_COLUMN_USERNAME = "UserName";
    private static final String USERS_COLUMN_PASSWORD = "Password";
    private static final String USERS_COLUMN_PROFILE_PICTURE = "ProfilePicture";

    private static final String TABLE_FAVOURITES = "Favourites";
    private static final String FAVOURITES_COLUMN_ID = "ID";
    private static final String FAVOURITES_COLUMN_USERNAME = "UserName";
    private static final String FAVOURITES_COLUMN_MOVIE_ID = "MovieId";

    private static final String TABLE_MOVIES = "Movie";
    private static final String MOVIES_COLUMN_ID = "ID";
    private static final String MOVIES_COLUMN_TITLE = "Title";
    private static final String MOVIES_COLUMN_DATE = "Date";
    private static final String MOVIES_COLUMN_DESCRIPTION = "Description";
    private static final String MOVIES_COLUMN_POSTER = "Poster";

    private static final String TABLE_IMAGES = "Images";
    private static final String IMAGES_COLUMN_ID = "ID";
    private static final String IMAGES_COLUMN_MOVIE_ID = "MovieId";
    private static final String IMAGES_COLUMN_IMAGE_PATH = "ImagePath";

    private static final String TABLE_VIDEOS = "Videos";
    private static final String VIDEOS_COLUMN_MOVIE_ID = "MovieId";
    private static final String VIDEOS_COLUMN_VIDEO_PATH = "VideoPath";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                USERS_COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                USERS_COLUMN_PASSWORD + " TEXT, " +
                USERS_COLUMN_PROFILE_PICTURE + " TEXT);"
        );

        db.execSQL("CREATE TABLE " + TABLE_MOVIES + " (" +
                MOVIES_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                MOVIES_COLUMN_TITLE + " TEXT, " +
                MOVIES_COLUMN_DATE + " TEXT, " +
                MOVIES_COLUMN_DESCRIPTION + " TEXT, " +
                MOVIES_COLUMN_POSTER + " TEXT);"
        );
        db.execSQL("CREATE TABLE " + TABLE_IMAGES + " (" +
                IMAGES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IMAGES_COLUMN_MOVIE_ID + " INTEGER, " +
                IMAGES_COLUMN_IMAGE_PATH + " TEXT);"
        );
        db.execSQL("CREATE TABLE " + TABLE_VIDEOS + " (" +
                VIDEOS_COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY, " +
                VIDEOS_COLUMN_VIDEO_PATH + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEOS);
        onCreate(db);
    }

    public boolean insertUser(String userName, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_USERNAME, userName);
        contentValues.put(USERS_COLUMN_PASSWORD, password);
        contentValues.put(USERS_COLUMN_PROFILE_PICTURE, "");

        long result = db.insert(TABLE_USERS,null, contentValues);
        if (result == -1)
            return  false;
        else
            return true;
    }

    public boolean userExists(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select 1 from " + TABLE_USERS + " where " + USERS_COLUMN_USERNAME + " = \"" + userName + "\"",null);
        if(res.getCount()==0) {
            return false;
        }
        else {
            return true;
        }

    }

    public String getPassword(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select " + USERS_COLUMN_PASSWORD +" from " + TABLE_USERS + " where " + USERS_COLUMN_USERNAME + " = \"" + userName + "\"",null);
        if(res.getCount()==0) {
            return "";
        }
        else {
            res.moveToNext();
            return res.getString(0);
        }
    }

    public boolean changeUsername(String oldUsername, String newUsername){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_USERNAME, newUsername);

        long result = db.update(TABLE_USERS, contentValues, USERS_COLUMN_USERNAME + " = \"" + oldUsername + "\"", null);
        if (result == -1)
            return  false;
        else
            return true;
    }

    public boolean changePassword(String username, String newPassword){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_PASSWORD, newPassword);

        long result = db.update(TABLE_USERS, contentValues, USERS_COLUMN_USERNAME + " = \"" + username + "\"", null);
        if (result == -1)
            return  false;
        else
            return true;
    }

}
