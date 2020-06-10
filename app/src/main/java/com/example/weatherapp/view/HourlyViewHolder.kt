package com.example.weatherapp.view

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.Hourly
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*
import java.text.SimpleDateFormat
import java.util.*

class HourlyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(hourly: Hourly, clickListener: (Hourly) -> Unit) {
       // val sdf = SimpleDateFormat("MM/dd/yy hh.mm aa")
        val sdf = SimpleDateFormat("hh.mm aa")
        val netDate = Date(hourly.dt.toLong() * 1000)
        val date = sdf.format(netDate)
        Log.d("dateTag", date)

        itemView.timeView.text = date.toString()
        itemView.degreeView.text = hourly.temp.toString()
        itemView.percentView.text = hourly.wind_speed.toString()
        var st = hourly.weather[0].icon

        Picasso.get().load("https://openweathermap.org/img/wn/$st@2x.png")
            .into(itemView.conditonView);

        itemView.setOnClickListener { clickListener(hourly) }
    }
}