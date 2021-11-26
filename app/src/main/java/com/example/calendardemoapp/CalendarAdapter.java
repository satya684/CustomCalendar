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

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.ConcurrentModificationException;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private Context context;
    private String[] months;
    private int year;
    DateAdapter dateAdapter;

    int days=0;
    int spaces=0;
    int Y = Globals.year;
    int pos = 0;

    public CalendarAdapter(Context context,String[] months,int year) {
        this.context=context;
        this.months=months;
        this.year=year;
    }

    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false);
        return new CalendarAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        holder.monthTV.setText(Globals.months[position]+" - "+Globals.year);

        pos = position+1;
        if(position==1){
            days=28;
        }else if(position==0 || position==2 || position==4 || position==6 || position==7) {
            days=31;
        }else if(position==8){
            days=30;
        }else if(position==9 || position==11){
            days=31;
        }else{
            days=30;
        }
        // check for leap year
        if  ((((Y % 4 == 0) && (Y % 100 != 0)) ||  (Y % 400 == 0)) && position == 1){
            days = 29;
        }
        // spaces required
//        Globals.startDayOfMonth = CalendarApplication.getInstance().getStartDay(pos,Y);

       /* Snackbar.make(parentLayout, "This is main activity", Snackbar.LENGTH_LONG)
                .show();*/

        if(Globals.selected_date.size()>0){
            String selected_date = Globals.selected_date.get(0).get("date")+"/"+
                    Globals.selected_date.get(0).get("month")+"/"+
                    Globals.selected_date.get(0).get("year");
            Toast.makeText(context,"Selected Date="+selected_date,Toast.LENGTH_SHORT).show();
        }
        Calendar c = Calendar.getInstance();
        c.set(Y, pos,1);
        c.add(Calendar.MONTH, -1);
        int day2 = c.get(Calendar.DAY_OF_WEEK);
        spaces = (day2-1);
        dateAdapter = new DateAdapter(context,days,pos,Y,spaces);
        holder.dateRV.setAdapter(dateAdapter);
    }

    @Override
    public int getItemCount() {
        return Globals.months.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView dateRV;
        private AppCompatTextView monthTV;
        GridLayoutManager layoutManager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            monthTV = itemView.findViewById(R.id.month_tv);
            dateRV = itemView.findViewById(R.id.date_rv);
            layoutManager = new GridLayoutManager(context,7,GridLayoutManager.VERTICAL,false);
            dateRV.setHasFixedSize(true);
            dateRV.setNestedScrollingEnabled(true);
            dateRV.setLayoutManager(layoutManager);
            dateAdapter = new DateAdapter(context,days,pos,Y,spaces);
            dateRV.setAdapter(dateAdapter);

        }
    }
}
