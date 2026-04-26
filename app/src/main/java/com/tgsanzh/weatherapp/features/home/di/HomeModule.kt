package com.tgsanzh.weatherapp.features.home.di

import com.tgsanzh.weatherapp.core.local.AppDatabase
import com.tgsanzh.weatherapp.features.home.data.local.dao.WeatherDao
import com.tgsanzh.weatherapp.features.home.data.local.mappers.WeatherConverter
import com.tgsanzh.weatherapp.features.home.data.repository.HomeRepositoryImpl
import com.tgsanzh.weatherapp.features.home.data.remote.service.HomeService
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository
import com.tgsanzh.weatherapp.features.home.domain.usecases.ObserveWeatherUseCase
import com.tgsanzh.weatherapp.features.home.domain.usecases.ObserveWeatherUseCaseImpl
import com.tgsanzh.weatherapp.features.home.domain.usecases.RefreshWeatherUseCase
import com.tgsanzh.weatherapp.features.home.domain.usecases.RefreshWeatherUseCaseImpl
import com.tgsanzh.weatherapp.features.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    single<WeatherDao> {
        get<AppDatabase>().weatherDao()
    }

    single<HomeService> { get<Retrofit>().create(HomeService::class.java) }
    single { WeatherConverter() }
    single<HomeRepository> { HomeRepositoryImpl(
        locationDataSource = get(),
        homeService = get(),
        dao = get(),
        converter = get(),
    ) }
    single<ObserveWeatherUseCase> { ObserveWeatherUseCaseImpl(get()) }
    single<RefreshWeatherUseCase> { RefreshWeatherUseCaseImpl(get()) }
    viewModel { HomeViewModel(get(), get()) }
}