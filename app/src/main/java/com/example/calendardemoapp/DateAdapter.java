package com.example.calendardemoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private Context context;
    private String[] months;
    private int year;
    private int days;
    private int month;
    private int spaces;
    private CalendarAdapter calendarAdapter;
    public DateAdapter(Context context,int days,int month,int year,int spaces) {
        this.context=context;
        this.days=days;
        this.month=month;
        this.year=year;
        this.spaces=spaces;
    }

    @NonNull
    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item, parent, false);
        return new DateAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.ViewHolder holder, int position) {

        int date_value=0;
        if(position==spaces){
            date_value=Globals.count;
            holder.date_tv.setText(String.valueOf(date_value));
        }else if(position>spaces){
            date_value=Globals.count+(position-spaces);
            holder.date_tv.setText(String.valueOf(date_value));
        }else{
            holder.date_tv.setText("");
        }

        HashMap<String,String> checkMap = new HashMap<String, String>();
        checkMap.put("year", String.valueOf(year));
        checkMap.put("month", String.valueOf(month));
        checkMap.put("date", holder.date_tv.getText().toString());
        if(Globals.currentMonth==month){
            if(date_value==Globals.currentDate){
                holder.date_tv.setTextColor(context.getResources().getColor(R.color.teal_200)); // Current Date
            }else if(date_value>Globals.currentDate){
                holder.date_tv.setTextColor(context.getResources().getColor(R.color.black)); // Upcoming Date
            }else{
                holder.date_tv.setTextColor(context.getResources().getColor(R.color.disable_color)); // Past Date
            }
        }else if(month>Globals.currentMonth){
            holder.date_tv.setTextColor(context.getResources().getColor(R.color.black));
        }else{
            holder.date_tv.setTextColor(context.getResources().getColor(R.color.disable_color));
        }

        if(Globals.selected_date.contains(checkMap)){
            holder.date_tv.setBackgroundColor(context.getResources().getColor(R.color.purple_500));
        }else{
            holder.date_tv.setBackgroundColor(context.getResources().getColor(R.color.white));
        }




        int finalDate_value = date_value;
        holder.date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month==Globals.currentMonth){
                    if(finalDate_value >=Globals.currentDate){
                        HashMap<String,String> insert_map=  new HashMap<>();
                        insert_map.put("year", String.valueOf(year));
                        insert_map.put("month", String.valueOf(month));
                        insert_map.put("date", holder.date_tv.getText().toString());
                        if(Globals.selected_date.size()==0 &&!Globals.selected_date.contains(insert_map)){
                            Globals.selected_date.add(insert_map);
                        }else if(Globals.selected_date.size()==0 && Globals.selected_date.contains(insert_map)){
                            Globals.selected_date.remove(insert_map);
                        }else if (Globals.selected_date.size()>0){
                            Globals.selected_date.clear();
                            Globals.selected_date.add(insert_map);
                        }

                        MainActivity.calendarAdapter.notifyDataSetChanged();
                    }
                }else if(month>Globals.currentMonth){
                    HashMap<String,String> insert_map=  new HashMap<>();
                    insert_map.put("year", String.valueOf(year));
                    insert_map.put("month", String.valueOf(month));
                    insert_map.put("date", holder.date_tv.getText().toString());
                    if(Globals.selected_date.size()==0 &&!Globals.selected_date.contains(insert_map)){
                        Globals.selected_date.add(insert_map);
                    }else if(Globals.selected_date.size()==0 && Globals.selected_date.contains(insert_map)){
                        Globals.selected_date.remove(insert_map);
                    }else if (Globals.selected_date.size()>0){
                        Globals.selected_date.clear();
                        Globals.selected_date.add(insert_map);
                    }
                    MainActivity.calendarAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return days+spaces;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView date_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_tv = itemView.findViewById(R.id.date_tv);
        }
    }
}
