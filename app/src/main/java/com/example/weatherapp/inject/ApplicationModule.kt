package com.example.weatherapp.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext():Context{
        return context
    }
}