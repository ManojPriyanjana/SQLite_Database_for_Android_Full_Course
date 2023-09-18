package com.manoj.understanddb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    // this is called the first time a database is accesed. There should be code to create a new database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    // this is called if the database version number changes.It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
