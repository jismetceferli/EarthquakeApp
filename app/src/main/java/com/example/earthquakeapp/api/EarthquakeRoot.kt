package com.example.earthquakeapp.api

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class EarthquakeRoot(
    val features: List<Feature>,
    val metadata: Metadata,
    val type: String
) : Parcelable

@Parcelize
data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: Properties,
    val type: String
) : Parcelable

@Parcelize
data class Metadata(
    val api: String,
    val count: Double,
    val generated: Long,
    val status: Double,
    val title: String,
    val url: String
) : Parcelable

@Parcelize
data class Geometry(
    val coordinates: List<Double>,
    val type: String
) : Parcelable

@Parcelize
data class Properties(
//    val alert: Any,
    val cdi: Double,
    val code: String,
    val detail: String,
    val dmin: Double,
    val felt: Double,
    val gap: Double,
    val ids: String,
    val mag: Double,
    val magType: String,
    val net: String,
    val nst: Double,
    val place: String,
    val rms: Double,
    val sig: Double,
    val sources: String,
    val status: String,
    val time: Long,
    val title: String,
    val tsunami: Double,
    val type: String,
    val types: String,
    val updated: Long,
    val url: String
) : Parcelable