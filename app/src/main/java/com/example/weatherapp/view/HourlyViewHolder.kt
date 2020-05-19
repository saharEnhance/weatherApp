package com.example.weatherapp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class HourlyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    fun bind(weather: Weather, clickListener: (String) -> Unit) {
        itemView.timeView.text = weather.time
       // itemView.conditonView.text=hourly.condition
        itemView.degreeView.text=weather.degree
        itemView.percentView.text=weather.percent

        Picasso.get().load("http://openweathermap.org/img/wn/10d@2x.png").into(itemView.conditonView);


        itemView.setOnClickListener { weather.toString()?.let { it1 -> clickListener(it1) } }

    }
}