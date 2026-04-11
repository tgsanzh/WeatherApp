package com.tgsanzh.weatherapp.di


import com.tgsanzh.weatherapp.core.network.AndroidNetworkChecker
import com.tgsanzh.weatherapp.core.network.NetworkChecker
import com.tgsanzh.weatherapp.core.network.NetworkConnectionInterceptor
import com.tgsanzh.weatherapp.core.network.getJson
import com.tgsanzh.weatherapp.core.network.getOkHttp
import com.tgsanzh.weatherapp.core.network.getRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single<NetworkChecker> {
        AndroidNetworkChecker(androidContext())
    }
    single { NetworkConnectionInterceptor(get()) }
    single { getOkHttp(get()) }
    single { getJson() }
    single { getRetrofit(get(), get()) }
}