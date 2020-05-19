package com.example.weatherapp.model

import io.reactivex.Single
import retrofit2.http.Query

interface WeatherRepository {
    fun getWeatherList(zip: String): Single<MutableList<Weather>>
}