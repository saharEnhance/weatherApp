package com.example.weatherapp.inject

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.model.WeatherDAO
import com.example.weatherapp.model.WeatherRoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule(application: WeatherApplication) {

    private val database:WeatherRoomDB = Room.databaseBuilder(application,
        WeatherRoomDB::class.java, "forecast_db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase():WeatherRoomDB{
        return database
    }
    @Singleton
    @Provides
    fun providesProductDao(database: WeatherRoomDB):WeatherDAO{
        return database.WeatherDAO()
    }
}