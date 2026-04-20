package com.tgsanzh.weatherapp.core.result

import com.tgsanzh.weatherapp.core.error.AppError

sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    data class Error(val error: AppError) : AppResult<Nothing>
}


