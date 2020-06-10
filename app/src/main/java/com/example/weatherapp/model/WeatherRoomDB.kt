package com.example.weatherapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Base::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class WeatherRoomDB : RoomDatabase() {

    abstract fun WeatherDAO(): WeatherDAO

    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDB? = null

        fun getInstance(context: Context): WeatherRoomDB {
            var tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    WeatherRoomDB::class.java,
                    "forecast_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
