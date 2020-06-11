package com.example.weatherapp.model

import io.reactivex.Single

interface WeatherRepository {

    fun getWeatherList(lat: Double, lon: Double): Single<Base>
    fun getWeatherListFromDatabase(): Single<Base>
}