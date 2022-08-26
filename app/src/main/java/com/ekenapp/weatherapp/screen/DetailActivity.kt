package com.ekenapp.weatherapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekenapp.weatherapp.R

class DetailActivity : AppCompatActivity() {
    private var cityName:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        cityName = savedInstanceState?.getString("city_name")
        val temp = ""
    }
}