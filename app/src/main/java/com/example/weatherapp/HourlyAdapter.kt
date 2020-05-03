package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HourlyAdapter(private var list:MutableList<Hourly>, private val clickListener: (String) -> Unit):RecyclerView.Adapter<HourlyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_horizontal, parent, false)
            return HourlyViewHolder(view)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }
}