package com.example.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Hourly

class HourlyAdapter(private var list : ArrayList<Hourly>, private val clickListener: (Hourly) -> Unit):RecyclerView.Adapter<HourlyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item, parent, false)
            return HourlyViewHolder(view)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    fun updateWeather(weatherList:ArrayList<Hourly>){
        this.list.clear()
        this.list.addAll(weatherList)
        notifyDataSetChanged()

    }

}