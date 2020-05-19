package com.example.weatherapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weather")
data class Weather
    (
    @PrimaryKey
    val time: String,
    val degree: String,
    val percent: String,
    val condition: String,
    val wind: String
) : Parcelable