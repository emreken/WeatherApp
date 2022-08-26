package com.ekenapp.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM cities ORDER BY id")
    fun loadAllCities(): LiveData<List<City>>

    @Insert
    fun insertNewCity(city: City)

    @Query("DELETE FROM cities WHERE name = :name")
    fun deleteCity(name: String)

    @Query("SELECT * FROM cities WHERE name = :name")
    fun loadCityByName(name: String): City?
}