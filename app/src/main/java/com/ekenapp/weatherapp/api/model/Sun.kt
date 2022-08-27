package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Sun(
    @SerializedName("Rise") var rise: String? = null,
    @SerializedName("Set") var set: String? = null,
)