package com.example.earthquakeapp

import com.example.earthquakeapp.util.CalendarUtil

class EarthquakeHandler {
    fun getDate(millis :Long) : String{
        val date = CalendarUtil()

        val splitDate = date.convertMilliSecondsToFormattedDate(millis.toString())!!.split(" ")[0]
        return splitDate
    }

    fun getTime(millis: Long): String {
        val date = CalendarUtil()

        val splitDate = date.convertMilliSecondsToFormattedDate(millis.toString())!!.split(" ")[1]
        return splitDate
    }
}