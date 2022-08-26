package com.ekenapp.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class SearchRecommendation(
    @SerializedName("Key") var cityKey : String? = null,
    @SerializedName("LocalizedName") var name : String? = null,
    @SerializedName("Country") var country : Country? = null,
)