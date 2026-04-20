package com.tgsanzh.weatherapp.features.home.data.remote.service

import com.tgsanzh.weatherapp.features.home.data.remote.dto.OneCallResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("data/3.0/onecall")
    suspend fun getWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("exclude") exclude: String,
    ): OneCallResponseDto
}