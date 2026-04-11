package com.tgsanzh.weatherapp.features.home.domain.usecases

import com.tgsanzh.weatherapp.core.location.domain.models.Location
import com.tgsanzh.weatherapp.core.location.domain.repository.LocationRepository

class GetLocationUseCaseImpl(
    val locationRepository: LocationRepository
): GetLocationUseCase {
    override suspend fun getLocation(): Location? {
        return locationRepository.GetCurrentLocation()
    }
}