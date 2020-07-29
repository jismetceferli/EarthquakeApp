package com.example.earthquakeapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakeapp.R
import com.example.earthquakeapp.api.Feature
import com.example.earthquakeapp.databinding.EarthquakeItemBinding
import com.example.recyclerviewkotlin.mvvm.EarthquakeViewModel

class EarthquakeAdapter(
    val earthquakeList: List<Feature>,
    private var listener: OnItemCLickListener
) :
    RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val quakeBinding: EarthquakeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.earthquake_item, parent, false
        )

        val handler = EarthquakeViewModel()
        quakeBinding.handler = handler

        return ViewHolder(quakeBinding)
    }

    override fun getItemCount(): Int {
        return earthquakeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = earthquakeList.get(position).properties
        holder.quakeBinding.earthquake = property
    }

    inner class ViewHolder(val quakeBinding: EarthquakeItemBinding) :
        RecyclerView.ViewHolder(quakeBinding.root),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.clickListener(adapterPosition)
        }
    }

    interface OnItemCLickListener {
        fun clickListener(position: Int)
    }
}