package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherRepositoryImp
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepositoryImp: WeatherRepositoryImp) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val stateMutableLiveData = MutableLiveData<AppState>()
    val stateLiveData: LiveData<AppState> get() = stateMutableLiveData
    var loaded = false

    fun getWeather(zip:String){

        stateMutableLiveData.value = AppState.LOADING
        disposable.add(
            weatherRepositoryImp.getWeatherList(zip).subscribe({
                loaded = true
                if (it.isEmpty()) {
                    stateMutableLiveData.value = AppState.ERROR("No weather Retrieved")
                } else {
                    stateMutableLiveData.value = AppState.SUCCESS(it)
                }
            }, {
                loaded = true
                //errors
                val errorString = when (it) {
                    is UnknownHostException -> "No Internet Connection"
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
        data class SUCCESS(val weatherList: MutableList<Weather>):AppState()
        data class ERROR(val message: String) : AppState()
    }
}

