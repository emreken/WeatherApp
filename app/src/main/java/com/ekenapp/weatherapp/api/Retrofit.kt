package com.ekenapp.weatherapp.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiInterface : ApiInterface by lazy {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dataservice.accuweather.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    return@lazy retrofit.create(ApiInterface::class.java)
}