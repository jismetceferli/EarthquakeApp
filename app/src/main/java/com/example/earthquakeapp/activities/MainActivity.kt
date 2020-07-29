package com.example.earthquakeapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakeapp.util.EarthquakeAdapter
import com.example.earthquakeapp.R
import com.example.earthquakeapp.api.EarthquakeRoot
import com.example.earthquakeapp.databinding.ActivityMainBinding
import com.example.recyclerviewkotlin.mvvm.EarthquakeViewModel
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
    EarthquakeAdapter.OnItemCLickListener {
    val TAG = "geek"

    companion object {
        const val SINGLE_QUAKE = 1
        const val ALL_QUAKES = 2
    }

    var earthquakeRoot: EarthquakeRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.showProgress = true

        val earthquakeViewModel = EarthquakeViewModel()

        activityMainBinding.quakeViewModel = earthquakeViewModel

        earthquakeViewModel.getAllEarthquakes().observe(this, object : Observer<EarthquakeRoot> {
            override fun onChanged(t: EarthquakeRoot?) {
                earthquakeRoot = t
                activityMainBinding.earthquakeRoot = t
                activityMainBinding.showProgress = false
                val earthquakeAdapter = EarthquakeAdapter(t!!.features, this@MainActivity)
                recycler_view.setHasFixedSize(true)
                recycler_view.layoutManager = LinearLayoutManager(applicationContext)
                recycler_view.adapter = earthquakeAdapter
            }
        })
    }

    override fun clickListener(position: Int) {
        val intent = Intent(this, EarthquakeMapsActivity::class.java)
        intent.putExtra("quakes", earthquakeRoot?.features?.get(position))
        intent.putExtra("fromWhere", SINGLE_QUAKE)
        startActivity(intent)
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}

