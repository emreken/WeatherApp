package com.ekenapp.weatherapp.utils
import com.ekenapp.weatherapp.database.City
import com.ekenapp.weatherapp.database.CityDao

class DatabaseUtil(private val dbInterface: CityDao) {
    fun isItInTheDatabase(name:String): Boolean {
        val res = dbInterface.loadCityByName(name)
        res?.let { return true } ?: run { return false }
    }

    fun addToTheDatabase(name:String) {
        dbInterface.insertNewCity(City(0, name))
    }

    fun removeFromDatabase(name:String) {
        dbInterface.deleteCity(name)
    }
}