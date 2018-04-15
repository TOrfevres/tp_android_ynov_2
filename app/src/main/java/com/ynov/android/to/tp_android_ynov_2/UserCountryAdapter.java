package com.ynov.android.to.tp_android_ynov_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.ynov.android.to.tp_android_ynov_2.bean.Country;
import com.ynov.android.to.tp_android_ynov_2.bean.CountryDAO;

import java.util.List;

public class UserCountryAdapter extends RecyclerView.Adapter<UserCountryViewHolder> {
    private List<Country> countries;
    private Context context;
    private String apiFlagsUrl;

    UserCountryAdapter(Context context, String apiFlagsUrl, List<Country> countries) {
        this.context = context;
        this.countries = countries;
        this.apiFlagsUrl = apiFlagsUrl;
    }

    @Override
    public UserCountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View countryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_country_item_view, parent, false);

        return new UserCountryViewHolder(countryView);
    }

    @Override
    public void onBindViewHolder(final UserCountryViewHolder holder, int position) {
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
        holder.getTvCountryDate().setText(countries.get(position).getDate());

        holder.getImgTrash().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountryDAO dao = new CountryDAO();
                dao.deleteCountry(context, countries.get(holder.getAdapterPosition()));
                countries.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
