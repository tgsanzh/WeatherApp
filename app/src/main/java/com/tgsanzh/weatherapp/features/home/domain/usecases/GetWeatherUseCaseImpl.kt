package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.error.AppError
import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWeatherUseCaseImpl(
    private val homeRepository: HomeRepository
) : GetWeatherUseCase {

    override fun observeWeather(): Flow<AppResult<Weather>> {
        return homeRepository.observeWeather()
            .map { weather ->
                if (weather.daily.isEmpty()) {
                    AppResult.Error(AppError.Network.NotFound)
                } else {
                    AppResult.Success(weather)
                }
            }
    }

    override suspend fun refresh(): AppResult<Unit> {
        return homeRepository.refresh()
    }
}