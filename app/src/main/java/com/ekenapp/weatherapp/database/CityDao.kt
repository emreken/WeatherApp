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

    @Query("DELETE FROM cities WHERE city_key = :cityKey")
    fun deleteCity(cityKey: String)

    @Query("SELECT * FROM cities WHERE city_key = :cityKey")
    fun loadCityByKey(cityKey: String): City?
}