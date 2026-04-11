package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.location.domain.models.Location
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository

class GetWeatherUseCaseImpl(
    val homeRepository: HomeRepository
): GetWeatherUseCase {
    override suspend fun getWeather(location: Location): Weather {
        return homeRepository.getWeather(location)
    }
}