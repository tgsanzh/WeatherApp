package com.tgsanzh.weatherapp.di


import androidx.room.Room
import com.tgsanzh.weatherapp.core.local.AppDatabase
import com.tgsanzh.weatherapp.core.network.AndroidNetworkChecker
import com.tgsanzh.weatherapp.core.network.NetworkChecker
import com.tgsanzh.weatherapp.core.network.NetworkConnectionInterceptor
import com.tgsanzh.weatherapp.core.network.getJson
import com.tgsanzh.weatherapp.core.network.getOkHttp
import com.tgsanzh.weatherapp.core.network.getRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    single<NetworkChecker> {
        AndroidNetworkChecker(androidContext())
    }
    single { NetworkConnectionInterceptor(get()) }
    single { getOkHttp(get(), androidContext()) }
    single { getJson() }
    single { getRetrofit(get(), get()) }
}