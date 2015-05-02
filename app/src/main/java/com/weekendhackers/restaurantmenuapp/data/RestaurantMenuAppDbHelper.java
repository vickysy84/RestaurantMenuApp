package com.weekendhackers.restaurantmenuapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Manages a local database for OOTD data.
 * Created by vickysy on 3/10/15.
 */
public class RestaurantMenuAppDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "restaurantmenuapp.db";

    public RestaurantMenuAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("create db", "create db");
        // Create a table to hold items.
        final String SQL_CREATE_ITEM_TABLE = "CREATE TABLE " + RestaurantMenuAppContract.ItemEntry.TABLE_NAME + " (" +
                RestaurantMenuAppContract.ItemEntry._ID + " INTEGER PRIMARY KEY," +
                RestaurantMenuAppContract.ItemEntry.COLUMN_ITEM_TYPE + " TEXT NOT NULL, " +
                RestaurantMenuAppContract.ItemEntry.COLUMN_IMG_PATH + " TEXT, " +
                RestaurantMenuAppContract.ItemEntry.COLUMN_NAME + " TEXT, " +
                RestaurantMenuAppContract.ItemEntry.COLUMN_DESCRIPTION + " TEXT " +
                " );";

        db.execSQL(SQL_CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RestaurantMenuAppContract.ItemEntry.TABLE_NAME);
        onCreate(db);
    }
}
