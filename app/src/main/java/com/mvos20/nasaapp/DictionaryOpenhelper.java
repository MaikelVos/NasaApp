package com.mvos20.nasaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TheRedHatter on 13-3-2018.
 */

public class DictionaryOpenhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rover_db";
    private static final  int DATABASE_VERSION = 19;

    public DictionaryOpenhelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //create tables
        String query = "CREATE TABLE rover_db (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "camId INTEGER  , " +
                "fullName TEXT, " +
                "URL TEXT" +
                "); ";
        db.execSQL(query);

    }

    //when version number changes this method is called use an ALTER db statement
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS rover_db;");
        onCreate(db);
    }
}