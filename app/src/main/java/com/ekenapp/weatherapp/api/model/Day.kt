package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("LongPhrase") var longPhrase: String? = null,
    @SerializedName("Wind") var wind: Wind? = null
)