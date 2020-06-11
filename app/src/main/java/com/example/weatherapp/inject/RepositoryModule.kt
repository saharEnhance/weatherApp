package com.example.weatherapp.inject

import com.example.weatherapp.model.WeatherDAO
import com.example.weatherapp.model.WeatherRepositoryImp
import com.example.weatherapp.model.WeatherRoomDB
import com.example.weatherapp.network.WeatherRestService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepositoryModule(weatherRestService: WeatherRestService,database: WeatherRoomDB):WeatherRepositoryImp{
        return WeatherRepositoryImp(weatherRestService,database)
    }
}