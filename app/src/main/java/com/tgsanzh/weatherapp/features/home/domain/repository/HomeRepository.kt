package com.tgsanzh.weatherapp.features.home.domain.repository

import com.tgsanzh.weatherapp.core.location.domain.models.Location
import com.tgsanzh.weatherapp.features.home.domain.models.Weather

interface HomeRepository {
    suspend fun getWeather(location: Location): Weather
}