package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Base
import com.example.weatherapp.model.Hourly
import com.example.weatherapp.model.WeatherDAO
import com.example.weatherapp.model.WeatherRepositoryImp
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepositoryImp: WeatherRepositoryImp) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val stateMutableLiveData = MutableLiveData<AppState>()
    val stateLiveData: LiveData<AppState> get() = stateMutableLiveData
    var loaded = false

    fun getWeather(lat: Double, lon: Double) {

        stateMutableLiveData.value = AppState.LOADING
        disposable.add(
            weatherRepositoryImp.getWeatherList(lat, lon).subscribe({
                // val mutableList: MutableList<MyDailyInfo> = mutableListOf()
                loaded = true
                if (it.equals(null)) {
                    stateMutableLiveData.value = AppState.ERROR("No weather Retrieved")
                } else {
                    /*   it.hourly.forEach { hourly ->
                           mutableList.add(MyDailyInfo(hourly.weather[0].icon, it.current.temp, it.daily[0].rain ) )
                       }*/
                   // weatherDAO?.updateCache(it)
                    stateMutableLiveData.value = AppState.SUCCESS(it,it.hourly)
                }
            }, {
                loaded = true
                //errors
                val errorString = when (it) {
                    is UnknownHostException -> it.localizedMessage
                    else -> it.localizedMessage
                }
                stateMutableLiveData.value = AppState.ERROR(errorString)
            })
        )
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
    sealed class AppState {
        object LOADING : AppState()
        data class SUCCESS(val weatherList: Base, val hourlyList: ArrayList<Hourly>) : AppState()
        data class ERROR(val message: String) : AppState()
    }
}