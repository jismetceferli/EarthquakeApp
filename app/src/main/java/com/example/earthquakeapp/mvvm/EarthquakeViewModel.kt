package com.example.recyclerviewkotlin.mvvm

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquakeapp.activities.EarthquakeMapsActivity
import com.example.earthquakeapp.activities.MainActivity
import com.example.earthquakeapp.api.EarthquakeRoot
import com.example.earthquakeapp.api.Properties
import com.example.earthquakeapp.util.CalendarUtil

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

}