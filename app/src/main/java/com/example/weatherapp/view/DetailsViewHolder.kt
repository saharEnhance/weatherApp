package com.example.weatherapp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_horizontal.view.*

class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(weather: Weather, clickListener: (String) -> Unit) {
        itemView.timeViewH.text = weather.time
        // itemView.conditonViewH.text=hourly.condition
        itemView.degreeViewH.text=weather.degree
        itemView.percentViewH.text=weather.percent
        itemView.windViewH.text=weather.wind
        Picasso.get().load("http://openweathermap.org/img/wn/10d@2x.png").into(itemView.conditonViewH);

        itemView.setOnClickListener { weather.toString()?.let { it1 -> clickListener(it1) } }

    }
}