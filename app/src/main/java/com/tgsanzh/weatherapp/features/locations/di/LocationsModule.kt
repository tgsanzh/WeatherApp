package com.tgsanzh.weatherapp.features.locations.di

import com.tgsanzh.weatherapp.features.locations.presentation.viewmodel.LocationsViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val locationsModule = module {
    viewModel { LocationsViewModel() }
}