package com.example.calendardemoapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Globals {

    public static String [] months = {"January","February","March",
                                     "April","May","June",
                                     "July","August","September",
                                      "October","November","December"};

    public static int year = 2021;
    public static int count = 1;
    public static int currentMonth = 0;
    public static int currentYear = 0;
    public static int currentDate = 0;

    public static ArrayList<HashMap<String,String>>selected_date = new ArrayList<>();
}
