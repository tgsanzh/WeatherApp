package com.tgsanzh.weatherapp.core.location.di

import com.google.android.gms.location.LocationServices
import com.tgsanzh.weatherapp.core.location.data.datasource.LocationDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val getLocationModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single { LocationDataSource(androidContext(), get()) }
}