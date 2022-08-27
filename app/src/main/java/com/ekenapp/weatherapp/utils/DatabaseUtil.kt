package com.ekenapp.weatherapp.utils
import com.ekenapp.weatherapp.database.City
import com.ekenapp.weatherapp.database.CityDao

class DatabaseUtil(private val dbInterface: CityDao) {
    fun isItInTheDatabase(cityKey:String): Boolean {
        val res = dbInterface.loadCityByKey(cityKey)
        res?.let { return true } ?: run { return false }
    }

    fun addToTheDatabase(city:City) {
        dbInterface.insertNewCity(city)
    }

    fun removeFromDatabase(cityKey:String) {
        dbInterface.deleteCity(cityKey)
    }
}