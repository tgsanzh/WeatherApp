package com.tgsanzh.weatherapp.features.home.data.repository_impl

import com.tgsanzh.weatherapp.core.location.domain.models.Location
import com.tgsanzh.weatherapp.features.home.data.service.HomeService
import com.tgsanzh.weatherapp.features.home.data.dto.toDomain
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    val homeService: HomeService
): HomeRepository {
    override suspend fun getWeather(location: Location): Weather {
        val result = homeService.getWeatherByLocation(
            lon = location.lon,
            lat = location.lat,
            apiKey = "9794f80fc878d08bf18e59b743438816",
            lang = "ru"
        ).toDomain()

        return result
    }
}