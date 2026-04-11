package com.tgsanzh.weatherapp.features.home.data.service

import com.tgsanzh.weatherapp.features.home.data.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("data/2.5/weather")
    suspend fun getWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): WeatherResponse
}