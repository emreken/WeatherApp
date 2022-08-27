package com.ekenapp.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenapp.weatherapp.api.Repository
import com.ekenapp.weatherapp.api.model.Forecast
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: Repository) : ViewModel() {
    val forecastResults: MutableLiveData<Forecast?> = MutableLiveData<Forecast?>()

    fun getForecastResults(cityKey: String) {
        viewModelScope.launch {
            val result = try {
                repository.getForecastResults(cityKey)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            forecastResults.value = result
        }
    }
}