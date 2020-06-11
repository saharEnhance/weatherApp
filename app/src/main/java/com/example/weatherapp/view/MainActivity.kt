package com.example.weatherapp.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.inject.WeatherApplication
import com.example.weatherapp.model.Base
import com.example.weatherapp.model.Hourly
import com.example.weatherapp.model.WeatherDAO
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.viewmodel.WeatherViewModelFactory
import com.google.android.gms.location.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val PERMISSION_ID = 42

    @Inject
    lateinit var viewModelFactory: WeatherViewModelFactory
    lateinit var viewModel: WeatherViewModel

    var adapter: HourlyAdapter? = null
    private var mMessage: String? = null

    lateinit var mFusedLocationClient: FusedLocationProviderClient
    var lat: Double = 0.0
    var lon: Double = 0.0

    var celOn: Boolean = false
 //   var dao: WeatherDAO?= null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as WeatherApplication).appComponent.inject(this)
        viewModel = viewModelFactory.create(WeatherViewModel::class.java)
        /*    // Verify the action and get the query
            if (Intent.ACTION_SEARCH == intent.action) {
                intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                    doMySearch(query)
                }
            }
    */
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = " "
        val seeDetails: Button = findViewById<Button>(R.id.SeeDetails)
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)

        viewModel.stateLiveData.observe(this, Observer { appState ->
            when (appState) {
                is WeatherViewModel.AppState.LOADING -> displayToast("Loading")
                is WeatherViewModel.AppState.SUCCESS -> {
                    displayWeather(appState.weatherList, appState.weatherList.hourly)

                    var dailyList = appState.weatherList.daily
                    intent.putParcelableArrayListExtra(
                        "parce",
                        dailyList as ArrayList<out Parcelable?>?
                    )
                }
                is WeatherViewModel.AppState.ERROR -> displayToast("error!!")
                else -> displayToast("Something Went Wrong... Try Again.")
            }
        })
        seeDetails.setOnClickListener {
            startActivity(intent)
        }
       // Log.d("dao = ", dao?.getWeather().toString())
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {

        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {

                        lat = location.latitude
                        lon = location.longitude
                        viewModel.getWeather(lat, lon)
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            lat = mLastLocation.latitude
            lon = mLastLocation.longitude
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    private fun displayWeather(weatherList: Base, hourly: ArrayList<Hourly>) {

        adapter?.updateWeather(hourly)
        initRecyclerView(hourly)
        if (celOn) {
            textTemp.text = weatherList.current.temp.toString() + "°C"
        }
        textTemp.text = weatherList.current.temp.toString() + "°F"
        textFeel.text = weatherList.current.feels_like.toString()
        textDescription.text = weatherList.current.weather[0].description
        var st = weatherList.current.weather[0].icon
        Picasso.get().load("https://openweathermap.org/img/wn/$st@2x.png").into(imageView)
        minMax.text = weatherList.current.humidity.toString()
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
        val netDate = Date(weatherList.current.dt.toLong() * 1000)
        val date = sdf.format(netDate)
        dateText.text = date
        minMax.text = weatherList.current.humidity.toString()
    }

    private fun initRecyclerView(hourly: ArrayList<Hourly>) {

        HourlyForecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = HourlyAdapter(hourly) { hourly: Hourly ->
            onHourlySelected(hourly)
        }
        HourlyForecast.adapter = adapter
    }

    private fun onHourlySelected(hourly: Hourly) {
        Toast.makeText(this, "Clicked: $hourly", Toast.LENGTH_LONG).show()
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
                //   item.setIcon(R.drawable.ic_favorite_foreground)
                //  item.isVisible = false
                celOn = true
                return true
            }
            R.id.option_farenheit -> {
                displayToast(getString(R.string.option_farenheit_message))
                //  item.setIcon(R.drawable.ic_backarrow_foreground)
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
}

