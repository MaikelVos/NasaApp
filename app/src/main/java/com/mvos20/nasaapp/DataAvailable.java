package com.mvos20.nasaapp;

import android.database.Cursor;

/**
 * Created by TheRedHatter on 14-3-2018.
 */

public interface DataAvailable {

    // Call back interface
    void onDataAvailable(Cursor cursor);

}
