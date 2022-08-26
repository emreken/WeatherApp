package com.ekenapp.weatherapp.database

import android.content.Context
import androidx.room.Room

object DatabaseInstance {
    fun getCityDatabaseDao(context: Context): CityDao {
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java, "city_database"
        ).build().getCityDao()
    }
}