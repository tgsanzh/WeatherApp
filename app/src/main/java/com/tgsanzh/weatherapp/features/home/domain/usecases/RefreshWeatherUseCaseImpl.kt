package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository

class RefreshWeatherUseCaseImpl(
    private val homeRepository: HomeRepository
) : RefreshWeatherUseCase {
    override suspend fun refresh(): AppResult<Unit> {
        return homeRepository.refresh()
    }
}