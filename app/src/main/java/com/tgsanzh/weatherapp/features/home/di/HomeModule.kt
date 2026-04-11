package com.tgsanzh.weatherapp.features.home.di

import com.tgsanzh.weatherapp.features.home.domain.usecases.GetLocationUseCase
import com.tgsanzh.weatherapp.features.home.domain.usecases.GetLocationUseCaseImpl
import com.tgsanzh.weatherapp.features.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<GetLocationUseCase> { GetLocationUseCaseImpl(get()) }
    viewModel { HomeViewModel(get()) }
}