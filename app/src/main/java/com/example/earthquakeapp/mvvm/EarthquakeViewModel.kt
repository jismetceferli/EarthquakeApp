package com.example.recyclerviewkotlin.mvvm

import android.app.Activity
import android.content.Intent
import android.icu.number.Precision
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquakeapp.activities.EarthquakeMapsActivity
import com.example.earthquakeapp.activities.MainActivity
import com.example.earthquakeapp.activities.NewsActivity
import com.example.earthquakeapp.api.EarthquakeRoot
import com.example.earthquakeapp.api.Properties
import com.example.earthquakeapp.util.CalendarUtil
import com.example.earthquakeapp.util.EarthquakeAdapter
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat

class EarthquakeViewModel : ViewModel() {
    val TAG = "geek"

    var userRepository: EartquakeRepository? = null
    var liveData: MutableLiveData<EarthquakeRoot>? = null

    init {
        userRepository = EartquakeRepository()
    }

    fun getAllEarthquakes(): LiveData<EarthquakeRoot> {

        if (liveData == null) {
            liveData = userRepository?.getEarthquakes()
        }
        return liveData!!
    }


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

    fun showAllEarthquakes(view: View, earthquakeRoot: EarthquakeRoot) {

        val intent = Intent(view.context, EarthquakeMapsActivity::class.java)
            intent.putExtra("quakes", earthquakeRoot)
            intent.putExtra("fromWhere", MainActivity.ALL_QUAKES)
            view.context.startActivity(intent)
    }

    fun roundOffDecimal(number: Double): Double? {

        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

    fun launchNewsActivity(view: View, uri: String) {

        val intent = Intent(view.context, NewsActivity::class.java)
        intent.putExtra("newsUri", uri)
        view.context.startActivity(intent)
    }

    fun finishActivity(view: View) {

        val activity: Activity = view.context as Activity
        activity.finish()
    }

}