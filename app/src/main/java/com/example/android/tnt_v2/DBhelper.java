package com.example.android.tnt_v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lakshmi on 7/7/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Expense.db";

    String sql = "CREATE TABLE expenses " +
            "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "d TEXT, " +
            "t TEXT, " +
            "c TEXT, " +
            "date TEXT ) ";


    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS expenses";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over


        db.execSQL(SQL_DELETE_ENTRIES);

        onCreate(db);
    }
    //public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //onUpgrade(db, oldVersion, newVersion);
    //}
}

