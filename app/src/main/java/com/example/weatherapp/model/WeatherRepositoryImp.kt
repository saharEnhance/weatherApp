package com.example.weatherapp.model

import com.example.weatherapp.network.WeatherRestService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherRestService: WeatherRestService,
    private val database: WeatherRoomDB
) : WeatherRepository {

    override fun getWeatherList(lat: Double, lon: Double, units: String): Single<Base> {
        return weatherRestService.getWeather(lat, lon, units).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getWeatherListFromDatabase(): Single<Base> {
        TODO("Not yet implemented")
        //check 30 min past otherwise call network
        //if true return data
        //if fal netw -> DB
//        val dO= database.WeatherDAO()
//        dO.
        // return database.WeatherDAO().getWeather()
    }


}