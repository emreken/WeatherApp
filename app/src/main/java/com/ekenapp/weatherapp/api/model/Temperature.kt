package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Minimum") var minimum: Minimum? = null,
    @SerializedName("Maximum") var maximum: Maximum? = null,
)