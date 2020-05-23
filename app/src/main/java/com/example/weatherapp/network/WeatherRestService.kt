package com.example.weatherapp.network

import com.example.weatherapp.model.Weather
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.util.concurrent.TimeUnit

interface WeatherRestService {

    companion object {
        val instance: WeatherRestService by lazy {
            //Logging
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //OkHttp
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Request-Type", "Android")
                            .addHeader("Content-Type", "application/json")
                            //.addHeader("x-rapidapi-host", "pro.openweathermap.org")
                           // .addHeader("x-rapidapi-key", "f7949a8e538ca7d71ca37964558b28b2")

                        val request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                }).build()
            //Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("pro.openweathermap.org")

                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(WeatherRestService::class.java)
        }
    }

    @GET("/data/2.5/forecast/hourly")
    fun getWeather(@Query("zip") zip: String): Single<MutableList<Weather>>

}