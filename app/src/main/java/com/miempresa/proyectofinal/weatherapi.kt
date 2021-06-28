package com.miempresa.apiclima

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface weatherapi {
    @GET("weather")
    fun getweather(
            @Query("lat") latitud: String,
            @Query("lon") longitud: String,
            @Query("appid") apikey: String
    ): Call<WeatherResponse>
}