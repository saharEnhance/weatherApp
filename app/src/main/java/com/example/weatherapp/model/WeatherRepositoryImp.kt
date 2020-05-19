package com.example.weatherapp.model

import io.reactivex.Single

class WeatherRepositoryImp : WeatherRepository {
    override fun getWeatherList(zip: String): Single<MutableList<Weather>> {
        TODO("Not yet implemented")
    }
}