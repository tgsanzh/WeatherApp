package com.tgsanzh.weatherapp.features.home.data.local.mappers

import com.tgsanzh.weatherapp.features.home.data.remote.dto.OneCallResponseDto
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherJsonConverterTest {
    @Test
    fun `weatherJsonConverter converts dto to json to dto` () {
        val mockResponse = OneCallResponseDto(
            lat = 123.0,
            lon = 121.0,
            timezone = "Almaty",
            timezoneOffset = 1,
            hourly = emptyList(),
            daily = emptyList(),
        )

        val converter = WeatherConverter()
        val convertedJson = converter.fromDto(mockResponse)

        val convertedDto = converter.toDto(convertedJson)

        assertEquals(convertedDto, mockResponse)
    }
}