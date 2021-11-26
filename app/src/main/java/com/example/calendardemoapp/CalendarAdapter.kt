package com.example.calendardemoapp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.calendardemoapp.DateAdapter
import com.example.calendardemoapp.Globals
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.calendardemoapp.R
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import java.util.*

class CalendarAdapter(
    private val context: Context,
    private val months: Array<String>,
    private val year: Int
) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    var dateAdapter: DateAdapter? = null
    var days = 0
    var spaces = 0
    var Y = Globals.year
    var pos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.monthTV.text = Globals.months[position].toString() + " - " + Globals.year
        pos = position + 1
        days = if (position == 1) {
            28
        } else if (position == 0 || position == 2 || position == 4 || position == 6 || position == 7) {
            31
        } else if (position == 8) {
            30
        } else if (position == 9 || position == 11) {
            31
        } else {
            30
        }
        // check for leap year
        if ((Y % 4 == 0 && Y % 100 != 0 || Y % 400 == 0) && position == 1) {
            days = 29
        }
        // spaces required
//        Globals.startDayOfMonth = CalendarApplication.getInstance().getStartDay(pos,Y);

        /* Snackbar.make(parentLayout, "This is main activity", Snackbar.LENGTH_LONG)
                .show();*/if (Globals.selected_date.size > 0) {
            val selected_date = Globals.selected_date[0]["date"].toString() + "/" +
                    Globals.selected_date[0]["month"] + "/" +
                    Globals.selected_date[0]["year"]
            Toast.makeText(context, "Selected Date=$selected_date", Toast.LENGTH_SHORT).show()
        }
        val c = Calendar.getInstance()
        c[Y, pos] = 1
        c.add(Calendar.MONTH, -1)
        val day2 = c[Calendar.DAY_OF_WEEK]
        spaces = day2 - 1
        dateAdapter = DateAdapter(context, days, pos, Y, spaces)
        holder.dateRV.adapter = dateAdapter
    }

    override fun getItemCount(): Int {
        return Globals.months.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateRV: RecyclerView
        val monthTV: AppCompatTextView
        var layoutManager: GridLayoutManager

        init {
            monthTV = itemView.findViewById(R.id.month_tv)
            dateRV = itemView.findViewById(R.id.date_rv)
            layoutManager = GridLayoutManager(context, 7, GridLayoutManager.VERTICAL, false)
            dateRV.setHasFixedSize(true)
            dateRV.isNestedScrollingEnabled = true
            dateRV.layoutManager = layoutManager
            dateAdapter = DateAdapter(context, days, pos, Y, spaces)
            dateRV.adapter = dateAdapter
        }
    }
}