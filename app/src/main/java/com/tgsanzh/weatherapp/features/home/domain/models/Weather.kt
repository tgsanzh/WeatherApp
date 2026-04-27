package com.tgsanzh.weatherapp.features.home.domain.models

data class Weather(
    val timezone: String,
    val timezoneOffset: Int,
    val updatedAt: Long,
    val hourly: List<Hourly>,
    val daily: List<Daily>
)

data class Hourly(
    val dt: Long,
    val temp: Double,
    val icon: String
)

data class Daily(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moonPhase: Double,
    val summary: String,
    val temp: Temp,
    val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val windSpeed: Double,
    val windDeg: Int,
    val windGust: Double?,
    val weather: List<WeatherInfo>,
    val clouds: Int,
    val pop: Double,
    val uvi: Double
)

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class WeatherInfo(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)