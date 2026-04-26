package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface ObserveWeatherUseCase {
     fun observeWeather(): Flow<AppResult<Weather>>
}