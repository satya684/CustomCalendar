package com.example.calendardemoapp

import java.util.ArrayList
import java.util.HashMap

object Globals {
    @JvmField
    var months = arrayOf(
        "January", "February", "March",
        "April", "May", "June",
        "July", "August", "September",
        "October", "November", "December"
    )
    @JvmField
    var year = 2021
    var count = 1
    @JvmField
    var currentMonth = 0
    @JvmField
    var currentYear = 0
    @JvmField
    var currentDate = 0
    var selected_date = ArrayList<HashMap<String, String>>()
}