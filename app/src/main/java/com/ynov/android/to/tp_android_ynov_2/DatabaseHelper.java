package com.ynov.android.to.tp_android_ynov_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String NOM_BASE = "country.db";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NOM_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE country (name varchar(255), capital varchar(255), region varchar(255), date varchar(255), code varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("CREATE TABLE country (name varchar(255), capital varchar(255), region varchar(255), date varchar(255), code varchar(255))");
    }
}
