package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.features.home.domain.models.Daily
import com.tgsanzh.weatherapp.features.home.domain.models.FeelsLike
import com.tgsanzh.weatherapp.features.home.domain.models.Temp
import com.tgsanzh.weatherapp.features.home.domain.models.WeatherInfo
import com.tgsanzh.weatherapp.core.error.AppError
import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


private val mockDaily = Daily(
    dt = 0L,
    sunrise = 0L,
    sunset = 0L,
    moonrise = 0L,
    moonset = 0L,
    moonPhase = 0.0,
    summary = "Clear sky",
    temp = Temp(
        day = 20.0,
        min = 15.0,
        max = 25.0,
        night = 12.0,
        eve = 18.0,
        morn = 16.0
    ),
    feelsLike = FeelsLike(
        day = 19.0,
        night = 11.0,
        eve = 17.0,
        morn = 15.0
    ),
    pressure = 1000,
    humidity = 60,
    dewPoint = 10.0,
    windSpeed = 5.0,
    windDeg = 180,
    windGust = 7.0,
    weather = listOf(
        WeatherInfo(
            id = 1,
            main = "Clear",
            description = "clear sky",
            icon = "01d"
        )
    ),
    clouds = 10,
    pop = 0.1,
    uvi = 3.0
)


class ObserveWeatherUseCaseImplTest {
    
    @Test
    fun `observeWeather returns error when daily list is empty` () = runBlocking {

        val weather = Weather(
            timezone = "Asia/Almaty",
            timezoneOffset = 18000,
            updatedAt = 0L,
            hourly = emptyList(),
            daily = emptyList()
        )

        val repository = object : HomeRepository {
            override fun observeWeather() = flowOf(weather)

            override suspend fun refresh(): AppResult<Unit> {
                throw UnsupportedOperationException("Not needed in this test")
            }
        }

        val useCase = ObserveWeatherUseCaseImpl(repository)
        val result = useCase.observeWeather().first()

        assertEquals(AppResult.Error(AppError.Network.NotFound), result)
    }

    @Test
    fun `observeWeather returns success when daily list is not empty` () = runBlocking {

        val weather = Weather(
            timezone = "Asia/Almaty",
            timezoneOffset = 18000,
            updatedAt = 0L,
            hourly = emptyList(),
            daily = listOf(
                mockDaily
            )
        )

        val repository = object : HomeRepository {
            override fun observeWeather() = flowOf(weather)

            override suspend fun refresh(): AppResult<Unit> {
                throw UnsupportedOperationException("Not needed in this test")
            }
        }

        val useCase = ObserveWeatherUseCaseImpl(repository)
        val result = useCase.observeWeather().first()

        assertEquals(AppResult.Success(weather), result)
    }
}
