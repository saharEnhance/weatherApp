package com.example.weatherapp.inject

import com.example.weatherapp.model.WeatherRepositoryImp
import com.example.weatherapp.viewmodel.WeatherViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule() {

    @Provides
    fun providesFactory(weatherRepositoryImp: WeatherRepositoryImp):WeatherViewModelFactory{
        return WeatherViewModelFactory((weatherRepositoryImp))
    }
}