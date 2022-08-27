package com.ekenapp.weatherapp.api

import com.ekenapp.weatherapp.api.model.Forecast
import com.ekenapp.weatherapp.api.model.SearchRecommendation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/locations/v1/cities/autocomplete?apikey=FtE598nrG66V12YZBMfDjIIkg911Fy6d")
    suspend fun getSearchRecommendations(@Query(value = "q") userText: String) : List<SearchRecommendation>

    @GET("/forecasts/v1/daily/5day/{city_key}?apikey=FtE598nrG66V12YZBMfDjIIkg911Fy6d&details=true")
    suspend fun getForecastResults(@Path(value = "city_key", encoded = true) cityKey: String) : Forecast
}