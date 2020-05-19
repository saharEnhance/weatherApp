package com.example.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather

class DetailsAdapter(private var list:MutableList<Weather>, private val clickListener: (String) -> Unit):RecyclerView.Adapter<DetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_horizontal, parent, false)
            return DetailsViewHolder(view)
    }
    override fun getItemCount(): Int =list.size
    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }
}