package com.example.calendardemoapp

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.AppCompatTextView
import android.os.Bundle
import android.view.View
import com.example.calendardemoapp.R
import com.example.calendardemoapp.Globals
import com.example.calendardemoapp.MainActivity
import com.example.calendardemoapp.CalendarAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private var calendarRV: RecyclerView? = null
    private var layoutManager: LinearLayoutManager? = null
    private var selected_tv: AppCompatTextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val c = Calendar.getInstance()
        Globals.currentMonth = c[Calendar.MONTH] + 1
        Globals.currentYear = c[Calendar.YEAR]
        Globals.currentDate = c[Calendar.DATE]
        calendarRV = findViewById(R.id.calendar_rv)
        //selected_tv = findViewById(R.id.selected_tv)
        selected_tv?.setVisibility(View.VISIBLE)
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        calendarRV?.setHasFixedSize(true)
        calendarRV?.setNestedScrollingEnabled(true)
        calendarRV?.setLayoutManager(layoutManager)
        calendarAdapter = CalendarAdapter(this, Globals.months, Globals.year)
        calendarRV?.setAdapter(calendarAdapter)
    }

    companion object {
        var calendarAdapter: CalendarAdapter? = null
    }
}