package com.example.earthquakeapp

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.earthquakeapp.activities.NewsActivity
import com.example.earthquakeapp.util.CalendarUtil

class EarthquakeHandler {
    val TAG = "geek"

    fun getDate(millis: Long): String {
        val date = CalendarUtil()

        val splitDate = date.convertMilliSecondsToFormattedDate(millis.toString())!!.split(" ")[0]
        return splitDate
    }

    fun getTime(millis: Long): String {
        val date = CalendarUtil()

        val splitDate = date.convertMilliSecondsToFormattedDate(millis.toString())!!.split(" ")[1]
        return splitDate
    }

    fun launchNewsActivity(view: View, uri: String) {

        val intent = Intent(view.context, NewsActivity::class.java)
        intent.putExtra(
            "newsUri",
            uri
        )
        view.context.startActivity(intent)

    }
}