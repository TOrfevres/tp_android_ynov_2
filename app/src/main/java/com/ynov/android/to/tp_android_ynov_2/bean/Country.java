package com.ynov.android.to.tp_android_ynov_2.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {
    private String name;
    private String capital;
    private String region;
    private String date;
    private String code;

    public Country(String name, String capital, String region, String date, String code) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.date = date;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(capital);
        parcel.writeString(region);
        parcel.writeString(date);
        parcel.writeString(code);
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    private Country(Parcel in) {
        name = in.readString();
        capital = in.readString();
        region = in.readString();
        date = in.readString();
        code = in.readString();
    }
}
