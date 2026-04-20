package com.tgsanzh.weatherapp.features.home.presentation

import com.tgsanzh.weatherapp.core.error.AppError
import com.tgsanzh.weatherapp.core.location.domain.models.Location

data class HomeUiState(
    val weather: WeatherUi? = null,
    val location: Location? = null,
    val error: AppError? = null,
    val isLoading: Boolean = false,
)

data class WeatherUi(
    val timezone: String,
    val timezoneOffset: Int,
    val today: DailyUi,
    val hourly: List<HourlyUi>,
    val daily: List<DailyUi>
)

data class HourlyUi(
    val hour: String,   // "14"
    val temp: Int,
    val iconRes: Int
)

data class DailyUi(
    val dayOfWeek: String, // "ПН"
    val dt: Long,

    val sunrise: String,   // "14:53"
    val sunset: String,    // "20:11"

    val moonrise: Long,
    val moonset: Long,
    val moonPhase: Double,

    val summary: String,

    val temperature: Int,
    val tempMin: Int,
    val tempMax: Int,

    val feelsLike: Int,

    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,

    val windSpeed: Int,
    val windDeg: Int,
    val windGust: Int,

    val description: String,
    val iconRes: Int,

    val clouds: Int,
    val pop: Double,
    val uvi: Double
)