package com.ynov.android.to.tp_android_ynov_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ynov.android.to.tp_android_ynov_2.bean.Country;
import com.ynov.android.to.tp_android_ynov_2.bean.CountryDAO;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private String API_COUNTRIES_FLAGS = "http://www.geognos.com/api/en/countries/flag/XX.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.user_countries_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        CountryDAO dao = new CountryDAO();
        UserCountryAdapter userCountryAdapter = new UserCountryAdapter(getApplicationContext(),
                API_COUNTRIES_FLAGS, dao.getCountries(this, true));
        recyclerView.setAdapter(userCountryAdapter);

        Button button = findViewById(R.id.countries_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CountriesActivity.class);
                startActivity(intent);
            }
        });
    }
}
