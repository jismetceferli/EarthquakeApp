package com.example.recyclerviewkotlin.api

import com.example.earthquakeapp.api.EarthquakeRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("2.5_day.geojson")
    fun getEarthquakes(): Call<EarthquakeRoot>
}

