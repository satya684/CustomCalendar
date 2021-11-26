package com.example.calendardemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView calendarRV;
    private LinearLayoutManager layoutManager;
    public static CalendarAdapter calendarAdapter;
    private AppCompatTextView selected_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Calendar c = Calendar.getInstance();
        Globals.currentMonth = c.get(Calendar.MONTH)+1;
        Globals.currentYear = c.get(Calendar.YEAR);
        Globals.currentDate = c.get(Calendar.DATE);

        calendarRV = findViewById(R.id.calendar_rv);
        selected_tv = findViewById(R.id.selected_tv);
        selected_tv.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        calendarRV.setHasFixedSize(true);
        calendarRV.setNestedScrollingEnabled(true);
        calendarRV.setLayoutManager(layoutManager);
        calendarAdapter = new CalendarAdapter(this,Globals.months,Globals.year);
        calendarRV.setAdapter(calendarAdapter);


    }
}