package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.model.WeatherRepositoryImp

class WeatherViewModelFactory(private val weatherRepositoryImp: WeatherRepositoryImp) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepositoryImp) as T
    }
}