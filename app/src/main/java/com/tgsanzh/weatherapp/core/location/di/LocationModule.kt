package com.tgsanzh.weatherapp.core.location.di

import com.google.android.gms.location.LocationServices
import com.tgsanzh.weatherapp.core.location.data.datasource.LocationDataSource
import com.tgsanzh.weatherapp.core.location.data.repository_impl.LocationRepositoryImpl
import com.tgsanzh.weatherapp.core.location.domain.repository.LocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single { LocationDataSource(androidContext(), get()) }
    single <LocationRepository> { LocationRepositoryImpl(get()) }
}