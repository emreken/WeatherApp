package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("Speed") var speed: Speed? = null
)