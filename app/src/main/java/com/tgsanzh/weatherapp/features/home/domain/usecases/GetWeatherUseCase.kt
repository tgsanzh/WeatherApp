package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.location.domain.models.Location
import com.tgsanzh.weatherapp.features.home.domain.models.Weather

interface GetWeatherUseCase {
    suspend fun getWeather(location: Location): Weather
}