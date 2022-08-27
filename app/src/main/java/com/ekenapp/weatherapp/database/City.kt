package com.ekenapp.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cities")
data class City(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "city_key") val cityKey: String,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "country_name") val countryName: String
): Serializable