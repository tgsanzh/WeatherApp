package com.tgsanzh.weatherapp.core.location.data.repository_impl

import com.tgsanzh.weatherapp.core.location.data.datasource.LocationDataSource
import com.tgsanzh.weatherapp.core.location.domain.models.Location
import com.tgsanzh.weatherapp.core.location.domain.repository.LocationRepository

class LocationRepositoryImpl(
    val dataSource: LocationDataSource
) : LocationRepository {
    override suspend fun GetCurrentLocation(): Location? {
        return dataSource.getLocation()
    }

}