package com.example.weatherapp.inject

import com.example.weatherapp.network.WeatherRestService
import dagger.Module
import dagger.Provides

@Module
class RestModule {
    @Provides
    fun restService(): WeatherRestService {
        return WeatherRestService.instance
    }
}