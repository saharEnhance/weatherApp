package com.example.weatherapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_horizontal.view.*

class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(hourly: Hourly, clickListener: (String) -> Unit) {
        itemView.timeViewH.text = hourly.time
        //itemView.timeView.text=hourly.degree

        // itemView.timeView.text=hourly.percent
        itemView.setOnClickListener { hourly.toString()?.let { it1 -> clickListener(it1) } }

    }
}