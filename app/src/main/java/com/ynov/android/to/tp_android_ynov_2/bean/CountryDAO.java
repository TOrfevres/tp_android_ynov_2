package com.ynov.android.to.tp_android_ynov_2.bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.ynov.android.to.tp_android_ynov_2.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    public List<Country> getCountries(Context context, boolean byDate) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbReader = databaseHelper.getReadableDatabase();

        List<Country> countries = new ArrayList<>();

        String[] projection = {BaseContract.CountryBaseContract.COLONNE_NAME,
                BaseContract.CountryBaseContract.COLONNE_CAPITAL,
                BaseContract.CountryBaseContract.COLONNE_REGION,
                BaseContract.CountryBaseContract.COLONNE_DATE,
                BaseContract.CountryBaseContract.COLONNE_CODE};

        String tri = byDate ?
                BaseContract.CountryBaseContract.COLONNE_DATE + " ASC " :
                BaseContract.CountryBaseContract.COLONNE_NAME + " DESC ";

        Cursor cursor = dbReader.query(
                BaseContract.CountryBaseContract.TABLE_COUNTRY,
                projection,
                null,
                null,
                null,
                null,
                tri,
                null);

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    countries.add(new Country(
                            cursor.getString(cursor.getColumnIndex(
                                    BaseContract.CountryBaseContract.COLONNE_NAME)),
                            cursor.getString(cursor.getColumnIndex(
                                    BaseContract.CountryBaseContract.COLONNE_CAPITAL)),
                            cursor.getString(cursor.getColumnIndex(
                                    BaseContract.CountryBaseContract.COLONNE_REGION)),
                            cursor.getString(cursor.getColumnIndex(
                                    BaseContract.CountryBaseContract.COLONNE_DATE)),
                            cursor.getString(cursor.getColumnIndex(
                                    BaseContract.CountryBaseContract.COLONNE_CODE))));
                    cursor.moveToNext();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                cursor.close();
            }
        }

        return countries;
    }

    public void createCountry(Context context, Country country) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbWriter = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseContract.CountryBaseContract.COLONNE_NAME, country.getName());
        values.put(BaseContract.CountryBaseContract.COLONNE_CAPITAL, country.getCapital());
        values.put(BaseContract.CountryBaseContract.COLONNE_REGION, country.getRegion());
        values.put(BaseContract.CountryBaseContract.COLONNE_DATE, country.getDate());
        values.put(BaseContract.CountryBaseContract.COLONNE_CODE, country.getCode());

        dbWriter.insert(BaseContract.CountryBaseContract.TABLE_COUNTRY, null, values);
    }

    public void deleteCountry(Context context, Country country) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbWriter = databaseHelper.getWritableDatabase();

        String selection = BaseContract.CountryBaseContract.COLONNE_CODE + " = ? ";
        String[] selectionArgs = {country.getCode()};

        dbWriter.delete(BaseContract.CountryBaseContract.TABLE_COUNTRY, selection, selectionArgs);
    }

    public boolean hasCountry(Context context, String name) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbReader = databaseHelper.getReadableDatabase();
        long num = DatabaseUtils.queryNumEntries(dbReader,
                BaseContract.CountryBaseContract.TABLE_COUNTRY,
                "name=?", new String[] {name});

        return num > 0;
    }
}
