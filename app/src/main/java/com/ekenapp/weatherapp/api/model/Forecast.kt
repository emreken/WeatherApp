package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("DailyForecasts") var dailyForecasts: List<DailyForecast>? = null
)