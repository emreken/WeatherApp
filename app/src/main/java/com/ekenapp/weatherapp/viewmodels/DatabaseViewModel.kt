package com.ekenapp.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ekenapp.weatherapp.database.City
import com.ekenapp.weatherapp.database.CityDao

class DatabaseViewModel(private val dbInterface: CityDao): ViewModel() {
    fun loadCities(): LiveData<List<City>> {
        return dbInterface.loadAllCities()
    }
}