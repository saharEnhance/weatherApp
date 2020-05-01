package com.example.weatherapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
       setSupportActionBar(toolbar)
        supportActionBar!!.title = " "
  /*      super.onCreate(savedInstanceState)
//        Set layout file to class
        setContentView(R.layout.activity_main)
//        initialize the recyclerView from the XML
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        Initializing the type of layout, here I have used LinearLayoutManager you can try GridLayoutManager
//        Based on your requirement to allow vertical or horizontal scroll , you can change it in  LinearLayout.VERTICAL
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//        Create an arraylist
        val dataList = ArrayList<Model>()
        dataList.add(Model("Phone", 1))
        dataList.add(Model("Watch", 2))
        dataList.add(Model("Note", 3))
        dataList.add(Model("Pin", 4))
//        pass the values to RvAdapter
        val rvAdapter = RvAdapter(dataList)
//        set the recyclerView to the adapter
        recyclerView.adapter = rvAdapter;*/

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
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {

            R.id.action_favorites ->
            {
                displayToast(getString(R.string.action_favorites_message))
                return true
            }
            R.id.action_status ->
            {
                displayToast(getString(R.string.action_status_message))
                return true
            }
            R.id.search ->
            {
                displayToast(getString(R.string.action_search_message))
                return true
            }
            else ->
            {
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun displayToast(message: String?) {
        Toast.makeText(applicationContext , message ,
            Toast.LENGTH_SHORT).show()
    }













}
