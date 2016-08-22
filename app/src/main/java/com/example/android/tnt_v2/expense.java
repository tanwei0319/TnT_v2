package com.example.android.tnt_v2;



/**
 * Created by Lakshmi on 7/7/2016.
 */
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.provider.BaseColumns;

public class expense {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.

    public String t;
    public String d;
    public String c;
    public String date;
    public int id;

    public static final String table = "table";
    public static final String description = "Description";
    public static final String entry_ID = "entryid";
    public static final String total = "Total";
    public static final String categ = "Category";
    //public static final String datee = "Date";

    public expense() {

    }
}
