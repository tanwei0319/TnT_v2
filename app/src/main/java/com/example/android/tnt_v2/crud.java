package com.example.android.tnt_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Lakshmi on 7/7/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lakshmi on 9/7/2016.
 */
public class crud extends DBhelper {

    public crud(Context context) {
        super(context);
    }

    public boolean create(expense exp) {

        ContentValues values = new ContentValues();

        values.put("d", exp.d);
        values.put("t", exp.t);
        values.put("c", exp.c);
        values.put("date", exp.date);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean creat = db.insert("expenses", null, values) > 0;
        db.close();

        return creat;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM expenses";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }
    /*public crud open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        return this;
    }

    public Cursor fetch() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { expense.datee,expense.categ,expense.description, expense.total};
        Cursor cursor = db.query("expenses", columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }*/

    public List<expense> read() {

        List<expense> recordsList = new ArrayList<expense>();

        String sql = "SELECT * FROM expenses ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String desc = cursor.getString(cursor.getColumnIndex("d"));
                String tot = cursor.getString(cursor.getColumnIndex("t"));
                String categ = cursor.getString(cursor.getColumnIndex("c"));
                String today = cursor.getString(cursor.getColumnIndex("date"));

                expense exp = new expense();
                exp.id = id;
                exp.d = desc;
                exp.t = tot;
                exp.c = categ;
                exp.date= today;

                recordsList.add(exp);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }


/*
    public List<expense> read() {

        List<expense> recordsList = new ArrayList<expense>();

        String sql = "SELECT * FROM expenses ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String desc = cursor.getString(cursor.getColumnIndex("d"));
                String tot = cursor.getString(cursor.getColumnIndex("t"));
                String categ = cursor.getString(cursor.getColumnIndex("c"));
                String today = cursor.getString(cursor.getColumnIndex("date"));

                expense exp = new expense();
                exp.id = id;
                exp.d = desc;
                exp.t = tot;
                exp.c = categ;
                exp.date= today;

                recordsList.add(exp);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }*/

    public expense readSingleRecord(int expenseId) {

        expense exp = null;

        String sql = "SELECT * FROM expenses WHERE id =  " + expenseId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String desc = cursor.getString(cursor.getColumnIndex("d"));
            String tot = cursor.getString(cursor.getColumnIndex("t"));
            String categ = cursor.getString(cursor.getColumnIndex("c"));
            String today = cursor.getString(cursor.getColumnIndex("date"));

            exp = new expense();
            exp.id = id;
            exp.d = desc;
            exp.t = tot;
            exp.c = categ;
            exp.date = today;

        }

        cursor.close();
        db.close();

        return exp;

    }

    public boolean update(expense exp) {

        ContentValues values = new ContentValues();

        values.put("d", exp.d);
        values.put("t", exp.t);
        values.put("c", exp.c);
        values.put("date", exp.date);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(exp.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("expenses", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;

    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("expenses", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }
}
