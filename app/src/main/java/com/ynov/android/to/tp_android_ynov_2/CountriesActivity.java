package com.ynov.android.to.tp_android_ynov_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ynov.android.to.tp_android_ynov_2.bean.Country;
import com.ynov.android.to.tp_android_ynov_2.bean.CountryDAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CountriesActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {
    private String API_COUNTRIES_INFOS = "https://restcountries.eu/rest/v2/all?fields=name;capital;alpha2Code;region";
    private String API_COUNTRIES_FLAGS = "http://www.geognos.com/api/en/countries/flag/XX.png";

    private GestureDetector gestureDetector = null;
    private ApiCountryAdapter apiCountryAdapter = null;
    private RecyclerView recyclerView;

    private List<Country> allCountries = new ArrayList<>();
    private List<Country> displayedCountries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        recyclerView = findViewById(R.id.api_countries_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        callApiGetCountries();

        recyclerView.addOnItemTouchListener(this);
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent event) {
                        return true;
                    }
                });

        Button button = findViewById(R.id.countries_seach_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ((EditText) findViewById(R.id.countries_seach_input))
                        .getText().toString().toLowerCase();
                List<Country> newList = new ArrayList<>();

                for (Country country : allCountries) {
                    String name = country.getName().toLowerCase();
                    if (name.contains(text)) {
                        newList.add(country);
                    }
                }

                displayedCountries.clear();
                displayedCountries.addAll(newList);
                apiCountryAdapter.setFilter(newList);
            }
        });
    }

    private void callApiGetCountries() {
        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

        client.get(API_COUNTRIES_INFOS,
                null, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String answer = new String(responseBody);
                        try {
                            List<Country> countries = new ArrayList<>();
                            JSONArray jsonArray = new JSONArray(answer);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject country = (JSONObject) jsonArray.get(i);

                                if (!country.has("name")) throw new Exception();

                                countries.add(new Country(country.getString("name"),
                                        country.getString("capital"),
                                        country.getString("region"),
                                        null,
                                        country.getString("alpha2Code")));
                            }

                            allCountries.addAll(countries);
                            displayedCountries.addAll(allCountries);

                            apiCountryAdapter = new ApiCountryAdapter(getApplicationContext(),
                                    API_COUNTRIES_FLAGS, allCountries);
                            recyclerView.setAdapter(apiCountryAdapter);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Oops, an error as occurred ...",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        String response = new String(responseBody);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (gestureDetector.onTouchEvent(e)) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());

            if (child != null) {
                int index = rv.getChildAdapterPosition(child);

                if (new CountryDAO().hasCountry(getApplicationContext(),
                        displayedCountries.get(index).getName())) {
                    warnDuplicate(displayedCountries.get(index).getName(), index);

                } else {
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    intent.putExtra("country", displayedCountries.get(index));
                    startActivity(intent);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private void warnDuplicate(String countryName, final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention !");
        builder.setMessage("Vous avez déjà ajouté au moins une fois le pays suivant : " + countryName + "\nVoulez vous continuer ?");

        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                intent.putExtra("country", displayedCountries.get(index));
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
