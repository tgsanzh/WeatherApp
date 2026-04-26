package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.result.AppResult

interface RefreshWeatherUseCase {
     suspend fun refresh(): AppResult<Unit>
}