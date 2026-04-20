package com.tgsanzh.weatherapp.features.home.data.remote.mappers

import com.tgsanzh.weatherapp.features.home.data.remote.dto.DailyDto
import com.tgsanzh.weatherapp.features.home.data.remote.dto.FeelsLikeDto
import com.tgsanzh.weatherapp.features.home.data.remote.dto.HourlyDto
import com.tgsanzh.weatherapp.features.home.data.remote.dto.OneCallResponseDto
import com.tgsanzh.weatherapp.features.home.data.remote.dto.TempDto
import com.tgsanzh.weatherapp.features.home.data.remote.dto.WeatherDto
import com.tgsanzh.weatherapp.features.home.domain.models.Daily
import com.tgsanzh.weatherapp.features.home.domain.models.FeelsLike
import com.tgsanzh.weatherapp.features.home.domain.models.Hourly
import com.tgsanzh.weatherapp.features.home.domain.models.Temp
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.models.WeatherInfo

fun OneCallResponseDto.toDomain(): Weather {
    return Weather(
        timezone = timezone,
        timezoneOffset = timezoneOffset,
        hourly = hourly.map { it.toDomain() },
        daily = daily.map { it.toDomain() }
    )
}

fun HourlyDto.toDomain(): Hourly {
    return Hourly(
        dt = dt,
        temp = temp,
        icon = weather.firstOrNull()?.icon.orEmpty()
    )
}

fun DailyDto.toDomain(): Daily {
    return Daily(
        dt = dt,
        sunrise = sunrise,
        sunset = sunset,
        moonrise = moonrise,
        moonset = moonset,
        moonPhase = moonPhase,
        summary = summary,
        temp = temp.toDomain(),
        feelsLike = feelsLike.toDomain(),
        pressure = pressure,
        humidity = humidity,
        dewPoint = dewPoint,
        windSpeed = windSpeed,
        windDeg = windDeg,
        windGust = windGust,
        weather = weather.map { it.toDomain() },
        clouds = clouds,
        pop = pop,
        uvi = uvi
    )
}

fun TempDto.toDomain(): Temp {
    return Temp(
        day = day,
        min = min,
        max = max,
        night = night,
        eve = eve,
        morn = morn
    )
}

fun FeelsLikeDto.toDomain(): FeelsLike {
    return FeelsLike(
        day = day,
        night = night,
        eve = eve,
        morn = morn
    )
}

fun WeatherDto.toDomain(): WeatherInfo {
    return WeatherInfo(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}