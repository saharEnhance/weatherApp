package com.example.weatherapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM weather")
    fun getWeather(): LiveData<Weather>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCache(
        weather: List<Weather>
    )
}