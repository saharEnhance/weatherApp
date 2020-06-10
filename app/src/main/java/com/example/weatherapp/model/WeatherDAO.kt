package com.example.weatherapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM forecast")
    fun getWeather(): LiveData<Base>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCache(
        weather: List<Base>
    )
}