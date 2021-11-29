package com.example.calendardemoapp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.calendardemoapp.CalendarAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.calendardemoapp.R
import com.example.calendardemoapp.Globals
import com.example.calendardemoapp.MainActivity
import androidx.appcompat.widget.AppCompatTextView
import java.util.HashMap

class DateAdapter(
    private val context: Context,
    private val days: Int,
    private val month: Int,
    private val year: Int,
    private val spaces: Int
) : RecyclerView.Adapter<DateAdapter.ViewHolder>() {
    private val months: Array<String>
        get() {
            TODO()
        }
    private val calendarAdapter: CalendarAdapter? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.date_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var date_value = 0
        if (position == spaces) {
            date_value = Globals.count
            holder.date_tv.text = date_value.toString()
        } else if (position > spaces) {
            date_value = Globals.count + (position - spaces)
            holder.date_tv.text = date_value.toString()
        } else {
            holder.date_tv.text = ""
            holder.date_tv.visibility = View.INVISIBLE
            holder.firstdivider?.setVisibility(View.INVISIBLE)
            holder.second?.setVisibility(View.INVISIBLE)
            holder.thdivider?.setVisibility(View.INVISIBLE)
            holder.firstdivider?.setVisibility(View.INVISIBLE)

        }
        val checkMap = HashMap<String, String>()
        checkMap["year"] = year.toString()
        checkMap["month"] = month.toString()
        checkMap["date"] = holder.date_tv.text.toString()
        if (Globals.currentMonth == month) {
            if (date_value == Globals.currentDate) {
                holder.date_tv.setTextColor(context.resources.getColor(R.color.teal_200)) // Current Date
            } else if (date_value > Globals.currentDate) {
                holder.date_tv.setTextColor(context.resources.getColor(R.color.black)) // Upcoming Date
            } else {
                holder.date_tv.setTextColor(context.resources.getColor(R.color.disable_color)) // Past Date
            }
        } else if (month > Globals.currentMonth) {
            holder.date_tv.setTextColor(context.resources.getColor(R.color.black))
        } else {
            holder.date_tv.setTextColor(context.resources.getColor(R.color.disable_color))
        }
        if (Globals.selected_date.contains(checkMap)) {
            holder.date_tv.setBackgroundColor(context.resources.getColor(R.color.purple_500))
        } else {
            holder.date_tv.setBackgroundColor(context.resources.getColor(R.color.white))

        }
        val finalDate_value = date_value
        holder.date_tv.setOnClickListener {
            if (month == Globals.currentMonth) {
                if (finalDate_value >= Globals.currentDate) {
                    val insert_map = HashMap<String, String>()
                    insert_map["year"] = year.toString()
                    insert_map["month"] = month.toString()
                    insert_map["date"] = holder.date_tv.text.toString()
                    if (Globals.selected_date.size == 0 && !Globals.selected_date.contains(
                            insert_map
                        )
                    ) {
                        Globals.selected_date.add(insert_map)
                    } else if (Globals.selected_date.size == 0 && Globals.selected_date.contains(
                            insert_map
                        )
                    ) {
                        Globals.selected_date.remove(insert_map)
                    } else if (Globals.selected_date.size > 0) {
                        Globals.selected_date.clear()
                        Globals.selected_date.add(insert_map)
                    }
                    MainActivity.calendarAdapter?.notifyDataSetChanged()
                }
            } else if (month > Globals.currentMonth) {
                val insert_map = HashMap<String, String>()
                insert_map["year"] = year.toString()
                insert_map["month"] = month.toString()
                insert_map["date"] = holder.date_tv.text.toString()
                if (Globals.selected_date.size == 0 && !Globals.selected_date.contains(insert_map)) {
                    Globals.selected_date.add(insert_map)
                } else if (Globals.selected_date.size == 0 && Globals.selected_date.contains(
                        insert_map
                    )
                ) {
                    Globals.selected_date.remove(insert_map)
                } else if (Globals.selected_date.size > 0) {
                    Globals.selected_date.clear()
                    Globals.selected_date.add(insert_map)
                }
                MainActivity.calendarAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return days + spaces
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date_tv: AppCompatTextView
        var firstdivider: View? = null
        var second: View? = null
        var thdivider: View? = null
        var foudivider: View? = null

        init {
            date_tv = itemView.findViewById(R.id.date_tv)
            firstdivider = itemView.findViewById(R.id.firstdivider)
            second = itemView.findViewById(R.id.second)
            thdivider = itemView.findViewById(R.id.thdivider)
            foudivider = itemView.findViewById(R.id.foudivider)
        }
    }
}