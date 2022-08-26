package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("LocalizedName") var name : String? = null,
)