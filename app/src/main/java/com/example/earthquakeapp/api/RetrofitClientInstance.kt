package com.example.recyclerviewkotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}