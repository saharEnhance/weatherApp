package com.example.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.model.Daily
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    private lateinit var adapter: DetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Hourly"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_backarrow_foreground)

        val dailyArrayList = intent.getParcelableArrayListExtra<Daily>("parce")?: ArrayList()
        initRecyclerView(dailyArrayList)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun initRecyclerView(daily: ArrayList<Daily>) {

        hourlyDetails.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = DetailsAdapter(daily) { daily: Daily ->
            partItemClicked(daily)
        }
        hourlyDetails.adapter = adapter
    }
    private fun partItemClicked(daily: Daily) {
        Toast.makeText(this, "Clicked: $daily", Toast.LENGTH_LONG).show()
    }
}