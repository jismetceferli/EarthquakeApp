package com.example.earthquakeapp.util

import java.text.SimpleDateFormat
import java.util.Calendar

class CalendarUtil {
    var dateFormat = "dd-MM-yyyy hh:mm"
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(dateFormat)

    fun convertMilliSecondsToFormattedDate(milliSeconds: String): String? {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds.toLong()
        return simpleDateFormat.format(calendar.time)
    }
}