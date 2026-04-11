package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.location.domain.models.Location

interface GetLocationUseCase {
    suspend fun getLocation(): Location?
}