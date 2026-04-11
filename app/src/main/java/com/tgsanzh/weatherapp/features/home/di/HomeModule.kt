package com.tgsanzh.weatherapp.features.home.di

import com.tgsanzh.weatherapp.features.home.data.repository_impl.HomeRepositoryImpl
import com.tgsanzh.weatherapp.features.home.data.service.HomeService
import com.tgsanzh.weatherapp.features.home.domain.repository.HomeRepository
import com.tgsanzh.weatherapp.features.home.domain.usecases.GetLocationUseCase
import com.tgsanzh.weatherapp.features.home.domain.usecases.GetLocationUseCaseImpl
import com.tgsanzh.weatherapp.features.home.domain.usecases.GetWeatherUseCase
import com.tgsanzh.weatherapp.features.home.domain.usecases.GetWeatherUseCaseImpl
import com.tgsanzh.weatherapp.features.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    single<HomeService> { get<Retrofit>().create(HomeService::class.java) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<GetWeatherUseCase> { GetWeatherUseCaseImpl(get()) }
    single<GetLocationUseCase> { GetLocationUseCaseImpl(get()) }
    viewModel { HomeViewModel(get(), get()) }
}