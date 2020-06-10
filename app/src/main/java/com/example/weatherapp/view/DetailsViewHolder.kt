package com.example.weatherapp.view

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.Daily
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_horizontal.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(daily: Daily, clickListener: (Daily) -> Unit) {
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
        val netDate = Date(daily.dt.toLong() * 1000)
        val date = sdf.format(netDate)
        itemView.timeViewH.text = date.toString()
        itemView.degreeViewH.text = daily.clouds.toString()
        itemView.percentViewH.text = daily.rain.toString()
        itemView.windViewH.text = daily.clouds.toString()

        var st = daily.weather[0].icon
        Log.d("detailSctivity", st)
        Picasso.get().load("http://openweathermap.org/img/wn/$st@2x.png")
            .into(itemView.conditonViewH)

        itemView.setOnClickListener { clickListener(daily) }

    }

}