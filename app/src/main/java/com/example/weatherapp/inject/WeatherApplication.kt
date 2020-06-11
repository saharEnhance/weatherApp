package com.example.weatherapp.inject

import android.app.Application

class WeatherApplication:Application() {

    val appComponent by lazy{
        initializeComponent()
    }

    private fun initializeComponent():AppComponent {
        return DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .viewModelModule(ViewModelModule())
            .restModule(RestModule())
            .repositoryModule(RepositoryModule())
            .daoModule(DaoModule(this))
            .build()
    }

}