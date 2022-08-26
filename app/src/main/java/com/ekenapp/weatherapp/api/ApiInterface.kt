package com.ekenapp.weatherapp.api

import com.ekenapp.weatherapp.api.model.SearchRecommendation
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/locations/v1/cities/autocomplete?apikey=FtE598nrG66V12YZBMfDjIIkg911Fy6d")
    suspend fun getSearchRecommendations(@Query(value = "q") userText: String) : List<SearchRecommendation>
}