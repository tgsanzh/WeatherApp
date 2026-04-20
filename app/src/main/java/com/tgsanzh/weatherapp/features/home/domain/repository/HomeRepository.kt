package com.tgsanzh.weatherapp.features.home.domain.repository

import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun observeWeather(): Flow<Weather>
    suspend fun refresh(): AppResult<Unit>
}