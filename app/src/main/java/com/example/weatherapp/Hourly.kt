package com.example.weatherapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Hourly (val time: String, val degree: String, val percent: String) : Parcelable