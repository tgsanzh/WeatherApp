package com.tgsanzh.weatherapp.features.home.domain.models

data class Weather(
    val city: String,
    val temperature: Double,
    val feelsLike: Double,
    val description: String,
    val icon: String,
    val windSpeed: Double,
    val humidity: Int
)