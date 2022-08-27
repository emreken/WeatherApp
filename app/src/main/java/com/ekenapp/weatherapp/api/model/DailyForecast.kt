package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("Date") var date: String? = null,
    @SerializedName("Sun") var sun: Sun? = null,
    @SerializedName("Temperature") var temperature: Temperature? = null,
    @SerializedName("HoursOfSun") var hoursOfSun: Double? = null,
    @SerializedName("Day") var day: Day? = null,
)