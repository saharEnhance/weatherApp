package com.example.weatherapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity:AppCompatActivity() {

    private lateinit var adapter: DetailsAdapter
    private var testData: MutableList<Weather> =
        mutableListOf(
            Weather(
                "10pm",
                "59°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "11pm",
                "9°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "12pm",
                "9°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "1pm",
                "5°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "2pm",
                "59°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "3pm",
                "59°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "10pm",
                "5°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "10pm",
                "59°",
                "15%",
                "cloudy",
                "N12MPH"
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Hourly"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_backarrow_foreground);
        initRecyclerView()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initRecyclerView() {

        hourlyDetails.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = DetailsAdapter(testData as MutableList<Weather>) { hourly: String ->
            partItemClicked(hourly)
        }
        hourlyDetails.adapter = adapter
    }
    private fun partItemClicked(hourly: String) {
        Toast.makeText(this, "Clicked: $hourly", Toast.LENGTH_LONG).show()
    }


}