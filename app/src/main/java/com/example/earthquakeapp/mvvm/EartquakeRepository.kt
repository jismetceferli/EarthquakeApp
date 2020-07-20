package com.example.recyclerviewkotlin.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.earthquakeapp.api.EarthquakeRoot
import com.example.recyclerviewkotlin.api.ApiService
import com.example.recyclerviewkotlin.api.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EartquakeRepository {
    val TAG = "geek"
    fun getEarthquakes(): MutableLiveData<EarthquakeRoot> {
        val liveData: MutableLiveData<EarthquakeRoot> = MutableLiveData()

        val retrofit = RetrofitClientInstance.retrofit

        val api = retrofit.create(ApiService::class.java)

        api.getEarthquakes().enqueue(object : Callback<EarthquakeRoot> {

            override fun onResponse(call: Call<EarthquakeRoot>, response: Response<EarthquakeRoot>) {
                Log.d(TAG, "onResponse: " + response.code())
                val userList: EarthquakeRoot = response.body()!!

                liveData.value = userList
            }

            override fun onFailure(call: Call<EarthquakeRoot>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t)
            }
        })

        return liveData
    }
}