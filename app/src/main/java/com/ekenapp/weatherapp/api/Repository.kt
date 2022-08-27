package com.ekenapp.weatherapp.api

import com.ekenapp.weatherapp.api.model.Forecast
import com.ekenapp.weatherapp.api.model.SearchRecommendation

class Repository {

    suspend fun getSearchRecommendations(userText: String) : List<SearchRecommendation> {
        return apiInterface.getSearchRecommendations(userText)
    }

    suspend fun getForecastResults(cityKey: String) : Forecast {
        return apiInterface.getForecastResults(cityKey)
    }
}