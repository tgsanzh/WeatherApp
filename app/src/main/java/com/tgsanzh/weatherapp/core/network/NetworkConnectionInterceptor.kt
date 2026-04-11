package com.tgsanzh.weatherapp.core.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    private val networkChecker: NetworkChecker
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkChecker.isConnected()) {
            throw NoInternetException()
        }

        return chain.proceed(chain.request())
    }
}