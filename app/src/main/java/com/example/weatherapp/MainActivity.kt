package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var mMessage: String? = null
    private lateinit var adapter: ForecastAdapter
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
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = " "
        val seeDetails: Button = findViewById<Button>(R.id.SeeDetails)


        seeDetails.setOnClickListener{
        val intent = Intent (this@MainActivity, HourlyActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE,mMessage)
            startActivity(intent)
        }
   /*     seeDetails!!.setOnClickListener { addFragment(HourlyFragment(), true, "HourlyFragment") }

        if (savedInstanceState == null) {
            val frg = HourlyFragment()
            supportFragmentManager.beginTransaction().add(R.id.frag, frg).commit();
        }*/

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

        HourlyForecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = ForecastAdapter(testData as MutableList<Hourly>) { hourly: String ->
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
