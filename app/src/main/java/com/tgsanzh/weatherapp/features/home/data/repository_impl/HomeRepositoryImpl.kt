package com.tgsanzh.weatherapp.features.home.data.repository_impl

import com.tgsanzh.weatherapp.core.error.toAppError
import com.tgsanzh.weatherapp.core.location.data.datasource.LocationDataSource
import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.data.local.dao.WeatherDao
import com.tgsanzh.weatherapp.features.home.data.local.entities.WeatherCacheEntity
import com.tgsanzh.weatherapp.features.home.data.local.mappers.WeatherConverter
import com.tgsanzh.weatherapp.features.home.data.local.mappers.toDomain
import com.tgsanzh.weatherapp.features.home.data.remote.service.HomeService
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import com.tgsanzh.weatherapp.BuildConfig

class HomeRepositoryImpl(
    private val locationDataSource: LocationDataSource,
    private val homeService: HomeService,
    private val dao: WeatherDao,
    private val converter: WeatherConverter,
) : HomeRepository {

    override fun observeWeather(): Flow<Weather> {
        return dao.observe()
            .filterNotNull()
            .map { it.toDomain(converter) }
    }

    override suspend fun refresh(): AppResult<Unit> {
        val location = when (val result = locationDataSource.getLocation()) {
            is AppResult.Error -> return result
            is AppResult.Success -> result.data
        }

        return try {
            val dto = homeService.getWeatherByLocation(
                lon = location.lon,
                lat = location.lat,
                lang = "ru",
                apiKey = BuildConfig.API_KEY,
                exclude = "minutely,current"
            )

            dao.insert(
                WeatherCacheEntity(
                    responseJson = converter.fromDto(dto),
                    updatedAt = System.currentTimeMillis()
                )
            )

            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Error(e.toAppError())
        }
    }
}