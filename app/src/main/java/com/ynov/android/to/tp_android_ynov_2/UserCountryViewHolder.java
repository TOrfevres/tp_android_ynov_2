package com.ynov.android.to.tp_android_ynov_2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ynov.android.to.tp_android_ynov_2.bean.Country;
import com.ynov.android.to.tp_android_ynov_2.bean.CountryDAO;

public class UserCountryViewHolder extends RecyclerView.ViewHolder {
    private TextView tvCountryName;
    private TextView tvCountryCapital;
    private TextView tvCountryRegion;
    private TextView tvCountryDate;
    private ImageView imgTrash;
    private ImageView imgFlag;

    UserCountryViewHolder(View itemView) {
        super(itemView);
        tvCountryName = itemView.findViewById(R.id.country_name);
        tvCountryCapital = itemView.findViewById(R.id.country_capital);
        tvCountryRegion = itemView.findViewById(R.id.country_region);
        tvCountryDate = itemView.findViewById(R.id.country_date);
        imgTrash = itemView.findViewById(R.id.country_delete);
        imgFlag = itemView.findViewById(R.id.country_flag);
    }

    public TextView getTvCountryName() {
        return tvCountryName;
    }

    public TextView getTvCountryCapital() {
        return tvCountryCapital;
    }

    public TextView getTvCountryRegion() {
        return tvCountryRegion;
    }

    public TextView getTvCountryDate() {
        return tvCountryDate;
    }

    public ImageView getImgTrash() {
        return imgTrash;
    }

    public ImageView getImgFlag() {
        return imgFlag;
    }
}
