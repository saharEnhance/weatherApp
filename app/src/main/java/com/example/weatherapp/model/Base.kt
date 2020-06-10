package com.example.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Type


/*@Parcelize
@Entity(tableName = "forecast")
data class Forecast
    (
    @PrimaryKey
    //@SerializedName("timezone")
    val time: String,
    val degree: String,
    val percent: String,
    val condition: String,
    val wind: String
) : Parcelable*/

// result generated from /json

@Parcelize
@Entity(tableName = "forecast")
@TypeConverters
data class Base(

    @PrimaryKey
    val timezone: String,
    val lat: Double,
    val lon: Double,
    val timezone_offset: Int,
    val current: Current,
    val hourly: ArrayList<Hourly>,
    val daily: ArrayList<Daily>
) : Parcelable

@Parcelize

data class Current(

    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: ArrayList<Weather>
) : Parcelable

@Parcelize
data class Daily(

    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val feels_like: Feels_like,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: ArrayList<Weather>,
    val clouds: Int,
    val rain: Double,
    val uvi: Double
) : Parcelable

@Parcelize
data class Feels_like(

    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable

@Parcelize
data class Hourly(

    val dt: Int,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val clouds: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: ArrayList<Weather>
) : Parcelable

@Parcelize
data class Temp(

    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable

@Parcelize
data class Weather(

    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable

class Converters {

    @TypeConverter
    fun dfromString(value: String): ArrayList<Daily> {
        val listType: Type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun dfromArrayLisr(list: ArrayList<Daily>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun hfromString(value: String): ArrayList<Hourly> {
        val listType: Type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun hfromArrayLisr(list: ArrayList<Hourly>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun clistToJson(value: Current?) = Gson().toJson(value)

    @TypeConverter
    fun cjsonToList(value: String) = Gson().fromJson(value, Current::class.java)

    @TypeConverter
    fun flistToJson(value: Feels_like?) = Gson().toJson(value)

    @TypeConverter
    fun fjsonToList(value: String) = Gson().fromJson(value, Feels_like::class.java)

    @TypeConverter
    fun tlistToJson(value: Temp?) = Gson().toJson(value)

    @TypeConverter
    fun tjsonToList(value: String) = Gson().fromJson(value, Temp::class.java)

}
/*
object Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: ArrayList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}*/
