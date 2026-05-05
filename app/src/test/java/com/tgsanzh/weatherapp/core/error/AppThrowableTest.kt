package com.tgsanzh.weatherapp.core.error

import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class AppThrowableTest {

    @Test
    fun `toAppError returns Unauthorized for 401 http exception`() {
        val response = Response.error<Any>(
            401,
            "".toResponseBody(null)
        )
        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(AppError.Network.Unauthorized, result)
    }

    @Test
    fun `toAppError returns NotFound for 404 http exception`() {
        val response = Response.error<Any>(
            404,
            "".toResponseBody(null)
        )
        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(AppError.Network.NotFound, result)
    }

    @Test
    fun `toAppError returns Server for 500-599 http exception`() {
        val response = Response.error<Any>(
            501,
            "".toResponseBody(null)
        )
        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(AppError.Network.Server, result)
    }

    @Test
    fun `toAppError returns Unknown for unknown http exception`() {
        val response = Response.error<Any>(
            602,
            "".toResponseBody(null)
        )
        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(AppError.Network.Unknown, result)
    }

    @Test
    fun `toAppError return NoInternet for IOException` () {
        val exception = IOException()

        val result = exception.toAppError()

        assertEquals(AppError.Network.NoInternet, result)
    }
}