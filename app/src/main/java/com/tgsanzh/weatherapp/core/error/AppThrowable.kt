package com.tgsanzh.weatherapp.core.error

import retrofit2.HttpException
import java.io.IOException

fun Throwable.toAppError(): AppError {
    return when (this) {

        is IOException -> {
            AppError.Network.NoInternet
        }

        is HttpException -> {
            when (code()) {
                401 -> AppError.Network.Unauthorized
                404 -> AppError.Network.NotFound
                in 500..599 -> AppError.Network.Server
                else -> AppError.Network.Unknown
            }
        }

        else -> AppError.Network.Unknown
    }
}
