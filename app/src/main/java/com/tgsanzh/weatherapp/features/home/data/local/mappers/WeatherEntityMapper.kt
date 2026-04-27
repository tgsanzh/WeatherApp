package com.tgsanzh.weatherapp.features.home.data.local.mappers

import com.tgsanzh.weatherapp.features.home.data.local.entities.WeatherCacheEntity
import com.tgsanzh.weatherapp.features.home.data.remote.mappers.toDomain
import com.tgsanzh.weatherapp.features.home.domain.models.Weather

fun WeatherCacheEntity.toDomain(converter: WeatherConverter, updatedAt: Long): Weather {
    val dto = converter.toDto(responseJson)
    return dto.toDomain(updatedAt)
}