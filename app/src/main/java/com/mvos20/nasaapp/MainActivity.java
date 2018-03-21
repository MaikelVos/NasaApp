package com.mvos20.nasaapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements DataAvailable {

    public final String TAG = this.getClass().getSimpleName();

    public final static String EXTRA_PIC_URL = "URL";
    public final static String EXTRA_FULLNAME = "fullName";
    public final static String EXTRA_ID = "camId";
    public  final static  String EXTRA_IDENTIFIER = "_id";
    private Cursor cursor;
    private Context mcontext;
    private  ContentAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mcontext = this;


        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(mcontext.getDatabasePath("rover_db").getPath(),
                    null,
                    SQLiteDatabase.OPEN_READWRITE);

            String query = "SELECT * FROM rover_db";
            cursor = db.rawQuery(query, null);

            RecyclerView rvView = (RecyclerView) findViewById(R.id.RecycleAuth);
            this.adapter = new ContentAdapter(cursor, new ContentAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                }
            });
            rvView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            rvView.setLayoutManager(new LinearLayoutManager(mcontext));
        }
        catch (SQLiteCantOpenDatabaseException e){
            // Connect and pass self for callback
            String[] urls = new String[] { "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=G8yW0tTiwRDhZiJ91mHfVc9UX9iGFy3TVBXPripS" };

            DataRequestAPI getRandomUser = new DataRequestAPI(MainActivity.this, mcontext);
            getRandomUser.execute(urls);

        }



    }

    @Override
    public void onDataAvailable(Cursor cursor) {
        Log.i(TAG, "data refresh" );
        RecyclerView rvView = (RecyclerView) findViewById(R.id.RecycleAuth);

        rvView.setLayoutManager(new LinearLayoutManager(mcontext));
        this.adapter = new ContentAdapter(cursor, new ContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        rvView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();

    }
}
