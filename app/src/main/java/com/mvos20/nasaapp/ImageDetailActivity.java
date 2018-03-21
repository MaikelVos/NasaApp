package com.mvos20.nasaapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Maikel Vos on 13-3-2018.
 */

import com.squareup.picasso.Picasso;

import static com.mvos20.nasaapp.MainActivity.EXTRA_ID;

public class ImageDetailActivity extends AppCompatActivity {

    private static final String TAG = DataRequestAPI.class.getSimpleName();
    private SQLiteDatabase myDb;
    private Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();


        setContentView(R.layout.activity_photo_full);
        ImageDetailActivity.ViewHolder viewHolder;
        viewHolder = new ImageDetailActivity.ViewHolder();


        //connection/ select statement
        String q = (String) extras.getSerializable(MainActivity.EXTRA_IDENTIFIER);

        DictionaryOpenhelper dictionaryOpenhelper = new DictionaryOpenhelper(this);
        myDb = dictionaryOpenhelper.getReadableDatabase();
        String query = "SELECT * FROM rover_db WHERE _id = " + q;
        cursor = myDb.rawQuery(query, null);
        Log.i(TAG, "id" + q );


         //position is 0 because the select statement will only get 1 row, if is needed for cursor to stop
        if (!cursor.moveToPosition(0)){
            return;
        }


        //getting data
        String _id = cursor.getString(cursor.getColumnIndex(MainActivity.EXTRA_IDENTIFIER));
        String fullPic = cursor.getString(cursor.getColumnIndex(MainActivity.EXTRA_PIC_URL));
        String fullName = cursor.getString(cursor.getColumnIndex(MainActivity.EXTRA_FULLNAME));



        //setting data
        viewHolder.fullName.setText(fullName);
        Picasso.get().load(fullPic).into(viewHolder.fullImage);
        myDb.close();

    }


    public class ViewHolder {
        public ImageView fullImage = (ImageView) findViewById(R.id.fullImage);
        public TextView fullName = (TextView) findViewById(R.id.fullName);

    }

}

