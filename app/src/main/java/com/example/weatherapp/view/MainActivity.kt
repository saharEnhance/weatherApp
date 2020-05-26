package com.example.weatherapp.view

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.viewmodel.WeatherViewModelFactory
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: HourlyAdapter
    var searched: String = "92626"
    private var weatherList: MutableList<Weather> = mutableListOf()
    private var mMessage: String? = null
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

        /*    // Verify the action and get the query
            if (Intent.ACTION_SEARCH == intent.action) {
                intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                    doMySearch(query)
                }
            }
    */
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = " "
        val seeDetails: Button = findViewById<Button>(R.id.SeeDetails)

        /*  val actionBar: ActionBar? = supportActionBar
          actionBar?.setCustomView(R.layout.switch_layout)
          actionBar?.displayOptions = ActionBar.DISPLAY_HOME_AS_UP
          val switchButton: Switch = findViewById(R.id.actionbar_switch)
          switchButton.setOnCheckedChangeListener(this)*/

        seeDetails.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, mMessage)
            startActivity(intent)
        }

        initRecyclerView()
    }


    private fun initRecyclerView() {

        HourlyForecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

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

        val searchItem = menu.findItem(R.id.search)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    displayToast("toasteeed")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
        displayToast(searchItem.toString())
        /*      // Get the SearchView and set the searchable configuration
              val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
              (menu.findItem(R.id.search).actionView as SearchView).apply {
                  // Assumes current activity is the searchable activity
                  setSearchableInfo(searchManager.getSearchableInfo(componentName))
                  setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
              }*/
        // val faren: MenuItem = menu.findItem(R.id.option_farenheit)
        // val cel: MenuItem = menu.findItem(R.id.option_celcius)

        return super.onCreateOptionsMenu(menu)
        // return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.option_celcius -> {
                displayToast(getString(R.string.option_celcius_message))
                item.setIcon(R.drawable.ic_favorite_foreground)
                //  item.isVisible = false


                return true
            }
            R.id.option_farenheit -> {
                displayToast(getString(R.string.option_farenheit_message))
                item.setIcon(R.drawable.ic_backarrow_foreground)
                //item.isVisible = false

                /*listView.setVisibility(View.VISIBLE)
                gv.setVisibility(View.INVISIBLE)*/

                return true
            }
            /*     R.id.search -> {
                     displayToast(getString(R.string.action_search_message))
                     return true
                 }*/
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

    fun FarCel(type: String, degree: Int): Int {
        if (type == "Celsius")
            return ((degree - 32) * 5) / 9
        else if (type == "Farenheit")
            return ((degree * 9) / 5) + 32
        return 0
    }


/*    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        //Toast.makeText(MainActivity::this, String)
        displayToast("switch clicked!!") }*/
}

