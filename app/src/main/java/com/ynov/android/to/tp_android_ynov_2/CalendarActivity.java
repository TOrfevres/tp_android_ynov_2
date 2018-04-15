package com.ynov.android.to.tp_android_ynov_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.ynov.android.to.tp_android_ynov_2.bean.Country;
import com.ynov.android.to.tp_android_ynov_2.bean.CountryDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendar);
        writeSelectedDate(calendarView.getDate());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
                    Date date = dateFormat.parse(dayOfMonth + "/" + (month+1) + "/" + year);
                    writeSelectedDate(date.getTime());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Oops, an error as occurred ...",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button = findViewById(R.id.countries_validate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CountryDAO dao = new CountryDAO();
                    Country country = getIntent().getParcelableExtra("country");

                    TextView tvYear = findViewById(R.id.calendar_year);
                    TextView tvDate = findViewById(R.id.calendar_date);

                    country.setDate(
                            tvDate.getText().toString().split(" ")[1] + " " +
                            tvDate.getText().toString().split(" ")[2] + " " +
                            tvYear.getText().toString());

                    dao.createCountry(getApplicationContext(), country);

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Oops, an error as occurred ...",
                            Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        });
    }

    private void writeSelectedDate(Long input) {
        TextView tvYear = findViewById(R.id.calendar_year);
        TextView tvDate = findViewById(R.id.calendar_date);

        DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM-YYYY", Locale.FRANCE);
        String date = dateFormat.format(new Date(input));

        tvYear.setText(date.split("-")[1]);
        tvDate.setText(date.split("-")[0]);
    }
}
