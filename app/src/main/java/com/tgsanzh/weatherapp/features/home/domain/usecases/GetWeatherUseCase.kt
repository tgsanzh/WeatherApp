package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface GetWeatherUseCase {
     fun observeWeather(): Flow<AppResult<Weather>>
     suspend fun refresh(): AppResult<Unit>
}