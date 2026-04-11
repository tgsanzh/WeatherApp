package com.tgsanzh.weatherapp.features.home.data.dto

import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val weather: List<WeatherDto>,
    val main: MainDto,
    val wind: WindDto,
    val name: String
)

@Serializable
data class WeatherDto(
    val description: String,
    val icon: String
)

@Serializable
data class MainDto(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int
)

@Serializable
data class WindDto(
    val speed: Double
)


fun WeatherResponse.toDomain(): Weather {
    return Weather(
        city = name,
        temperature = main.temp,
        feelsLike = main.feels_like,
        description = weather.firstOrNull()?.description ?: "",
        icon = weather.firstOrNull()?.icon ?: "",
        windSpeed = wind.speed,
        humidity = main.humidity
    )
}