package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_hourly.*


class HourlyActivity:AppCompatActivity() {

    private lateinit var adapter: HourlyAdapter
    private var testData: MutableList<Hourly> =
        mutableListOf(
            Hourly("10pm", "10", "15%"),
            Hourly("11pm", "10", "15%"),
            Hourly("12pm", "10", "15%"),
            Hourly("1pm", "10", "15%"),
            Hourly("2pm", "10", "15%"),
            Hourly("3pm", "10", "15%"),
            Hourly("10pm", "10", "15%"),
            Hourly("10pm", "10", "15%")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly)
        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Hourly"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        initRecyclerView()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initRecyclerView() {

        hourlyDetails.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = HourlyAdapter(testData as MutableList<Hourly>) { hourly: String ->
            partItemClicked(hourly)
        }
        hourlyDetails.adapter = adapter
    }
    private fun partItemClicked(hourly: String) {
        Toast.makeText(this, "Clicked: $hourly", Toast.LENGTH_LONG).show()
    }


}