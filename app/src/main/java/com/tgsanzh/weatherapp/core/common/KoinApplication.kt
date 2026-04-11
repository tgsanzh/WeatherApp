package com.tgsanzh.weatherapp.core.common

import android.app.Application
import com.tgsanzh.weatherapp.core.location.di.locationModule
import com.tgsanzh.weatherapp.di.networkModule
import com.tgsanzh.weatherapp.features.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApplication)
            modules(
                networkModule,
                locationModule,
                homeModule,
            )
        }
    }
}