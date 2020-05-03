package com.example.weatherapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    fun bind(hourly: Hourly, clickListener: (String) -> Unit) {
        itemView.timeView.text = hourly.time
        //itemView.timeView.text=hourly.degree

        // itemView.timeView.text=hourly.percent
        itemView.setOnClickListener { hourly.toString()?.let { it1 -> clickListener(it1) } }

    }
}