package com.example.earthquakeapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.earthquakeapp.EarthquakeHandler
import com.example.earthquakeapp.R
import com.example.earthquakeapp.api.EarthquakeRoot
import com.example.earthquakeapp.api.Feature
import com.example.earthquakeapp.databinding.ActivityEarthquakeMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_earthquake_maps.*


class EarthquakeMapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    var markers = hashMapOf<String, Int>()

    private lateinit var mMap: GoogleMap
    val TAG = "geek"
    var bottomSheet: BottomSheetBehavior<View>? = null
    var earthquakeMapsBinding: ActivityEarthquakeMapsBinding? = null
    var earthquakeRoot: Feature? = null
    var allQuakes: EarthquakeRoot? = null
    var fromWhere: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        earthquakeMapsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_earthquake_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMarkerClickListener(this)

        val earthquakeHandler = EarthquakeHandler()
        earthquakeMapsBinding?.earthquakeHandler = earthquakeHandler

        bottomSheet = BottomSheetBehavior.from(bottom_sheet_quake)

        fromWhere = intent.getIntExtra("fromWhere", 1)

        if (fromWhere == MainActivity.SINGLE_QUAKE) {
            earthquakeRoot = intent.getParcelableExtra("quakes")!!
            addMarker(earthquakeRoot!!)

            earthquakeMapsBinding?.quakeDetail = earthquakeRoot
            bottomSheet!!.state = BottomSheetBehavior.STATE_EXPANDED

        } else {
            bottomSheet!!.state = BottomSheetBehavior.STATE_HIDDEN
            allQuakes = intent.getParcelableExtra("quakes")!!

            for (i in 0 until allQuakes!!.features.size) {
                addMarker(allQuakes!!.features.get(i), i)
            }
        }
    }

    fun addMarker(feature: Feature, id: Int? = null) {

        val place = LatLng(feature.geometry.coordinates.get(1), feature.geometry.coordinates.get(0))

        val mo = MarkerOptions()
            .position(place)
            .flat(true)
            .title(feature.properties.title)

        val marker: Marker = mMap.addMarker(mo)
        mMap.animateCamera(CameraUpdateFactory.newLatLng(place))
        if (id != null) {
            markers.put(marker.id, id)
        }
    }

    override fun onMarkerClick(p0: Marker?): Boolean {

        if (fromWhere == MainActivity.ALL_QUAKES) {
            bottomSheet!!.state = BottomSheetBehavior.STATE_EXPANDED
            earthquakeMapsBinding?.quakeDetail = allQuakes?.features?.get(markers.get(p0?.id)!!)
        }
        return false
    }

}
