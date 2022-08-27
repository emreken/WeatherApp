package com.ekenapp.weatherapp.utils

import com.ekenapp.weatherapp.database.City

interface SearchItemClick {
    fun onSearchItemClick(city: City)
}