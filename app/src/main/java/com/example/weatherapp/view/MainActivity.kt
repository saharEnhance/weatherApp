package com.example.weatherapp.view

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather
import com.example.weatherapp.network.WeatherRestService
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

   // lateinit var apiService: WeatherRestService
    private var mMessage: String? = null
    private lateinit var adapter: HourlyAdapter
    private var testData: MutableList<Weather> =
        mutableListOf(
            Weather(
                "6pm",
                "80°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "11pm",
                "10°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "12pm",
                "10",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "1pm",
                "10°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "2pm",
                "10°",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "3pm",
                "10",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "10pm",
                "10",
                "15%",
                "cloudy",
                "N12MPH"
            ),
            Weather(
                "10pm",
                "10",
                "15%",
                "cloudy",
                "N12MPH"
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = " "
        val seeDetails: Button = findViewById<Button>(R.id.SeeDetails)


        seeDetails.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, mMessage)
            startActivity(intent)
        }
        /*     seeDetails!!.setOnClickListener { addFragment(HourlyFragment(), true, "HourlyFragment") }

             if (savedInstanceState == null) {
                 val frg = HourlyFragment()
                 supportFragmentManager.beginTransaction().add(R.id.frag, frg).commit();
             }*/
       // println("=========" + (apiService.getWeather("94040,us") ))
        //Log.d(("======"),(apiService.getWeather("94040,us").toString()))
        initRecyclerView()
    }

/*
    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.frag, fragment, tag)
        ft.commit()
    }
*/


    private fun initRecyclerView() {

        HourlyForecast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = HourlyAdapter(testData as MutableList<Weather>) { hourly: String ->
            partItemClicked(hourly)
        }
        HourlyForecast.adapter = adapter
    }

    private fun partItemClicked(hourly: String) {
        Toast.makeText(this, "Clicked: $hourly", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        // Associate searchable configuration with the SearchView
        /*   val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
           (menu.findItem(R.id.search).actionView as SearchView).apply {
               setSearchableInfo(searchManager.getSearchableInfo(componentName))
           }*/

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_favorites -> {
                displayToast(getString(R.string.action_favorites_message))
                return true
            }
            R.id.action_status -> {
                displayToast(getString(R.string.action_status_message))
                return true
            }
            R.id.search -> {
                displayToast(getString(R.string.action_search_message))
                return true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun displayToast(message: String?) {
        Toast.makeText(
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
