package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Speed(
    @SerializedName("Value") var value:Double? = null,
    @SerializedName("Unit") var unit:String? = null
)