package com.tgsanzh.weatherapp.features.home.data.local.mappers

import androidx.room.TypeConverter
import com.tgsanzh.weatherapp.features.home.data.remote.dto.OneCallResponseDto
import kotlinx.serialization.json.Json

class WeatherConverter {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromDto(dto: OneCallResponseDto): String {
        return json.encodeToString(dto)
    }

    @TypeConverter
    fun toDto(jsonString: String): OneCallResponseDto {
        return json.decodeFromString(jsonString)
    }
}