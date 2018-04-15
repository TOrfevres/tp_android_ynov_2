package com.ynov.android.to.tp_android_ynov_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.ynov.android.to.tp_android_ynov_2.bean.Country;

import java.util.ArrayList;
import java.util.List;

public class ApiCountryAdapter extends RecyclerView.Adapter<ApiCountryViewHolder> {
    private List<Country> countries;
    private Context context;
    private String apiFlagsUrl;

    ApiCountryAdapter(Context context, String apiFlagsUrl, List<Country> countries) {
        this.context = context;
        this.countries = countries;
        this.apiFlagsUrl = apiFlagsUrl;
    }

    @Override
    public ApiCountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View countryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.api_country_item_view, parent, false);

        return new ApiCountryViewHolder(countryView);
    }

    @Override
    public void onBindViewHolder(ApiCountryViewHolder holder, int position) {
        Picasso.with(context)
                .load(apiFlagsUrl.replace("XX", countries.get(position).getCode()))
                .error(context.getResources().getDrawable(R.drawable.error))
                .fit().centerInside()
                .into(holder.getImgFlag());

        holder.getTvCountryName().setText(countries.get(position).getName());
        holder.getTvCountryCapital().setText(
                context.getString(R.string.country_capital_label, countries.get(position).getCapital()));
        holder.getTvCountryRegion().setText(
                context.getString(R.string.country_region_label, countries.get(position).getRegion()));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void setFilter(List<Country> newList) {
        countries = new ArrayList<>();
        countries.addAll(newList);
        notifyDataSetChanged();
    }
}
