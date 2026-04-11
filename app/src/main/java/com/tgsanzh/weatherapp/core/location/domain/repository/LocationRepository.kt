package com.tgsanzh.weatherapp.core.location.domain.repository

import com.tgsanzh.weatherapp.core.location.domain.models.Location

interface LocationRepository {
    suspend fun GetCurrentLocation(): Location?
}