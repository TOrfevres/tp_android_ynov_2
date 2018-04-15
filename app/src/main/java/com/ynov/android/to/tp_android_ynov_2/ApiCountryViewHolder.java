package com.ynov.android.to.tp_android_ynov_2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ApiCountryViewHolder extends RecyclerView.ViewHolder {
    private TextView tvCountryName;
    private TextView tvCountryCapital;
    private TextView tvCountryRegion;
    private ImageView imgFlag;

    ApiCountryViewHolder(View itemView) {
        super(itemView);
        tvCountryName = itemView.findViewById(R.id.country_name);
        tvCountryCapital = itemView.findViewById(R.id.country_capital);
        tvCountryRegion = itemView.findViewById(R.id.country_region);
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

    public ImageView getImgFlag() {
        return imgFlag;
    }
}
