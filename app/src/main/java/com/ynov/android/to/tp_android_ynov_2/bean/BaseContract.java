package com.ynov.android.to.tp_android_ynov_2.bean;

import android.provider.BaseColumns;

public class BaseContract {
    public BaseContract() {
        //
    }

    static class CountryBaseContract implements BaseColumns {
        static final String TABLE_COUNTRY = "country";
        static final String COLONNE_NAME = "name";
        static final String COLONNE_CAPITAL = "capital";
        static final String COLONNE_REGION = "region";
        static final String COLONNE_DATE = "date";
        static final String COLONNE_CODE = "code";
    }
}
