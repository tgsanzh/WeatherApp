package com.tgsanzh.weatherapp.core.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

fun getOkHttp(
    networkConnectionInterceptor: NetworkConnectionInterceptor,
    context: Context
): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .addInterceptor(networkConnectionInterceptor)
        .readTimeout(60_000, TimeUnit.MILLISECONDS)
        .writeTimeout(60_000, TimeUnit.MILLISECONDS)
        .build()


fun getJson(): Json {
    return Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
}

fun getRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
}