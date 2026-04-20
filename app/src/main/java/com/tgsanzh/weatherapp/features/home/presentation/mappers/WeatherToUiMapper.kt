package com.tgsanzh.weatherapp.features.home.presentation.mappers

import com.tgsanzh.weatherapp.R
import com.tgsanzh.weatherapp.features.home.domain.models.Daily
import com.tgsanzh.weatherapp.features.home.domain.models.FeelsLike
import com.tgsanzh.weatherapp.features.home.domain.models.Hourly
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.presentation.DailyUi
import com.tgsanzh.weatherapp.features.home.presentation.HourlyUi
import com.tgsanzh.weatherapp.features.home.presentation.WeatherUi
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.collections.get
import kotlin.math.roundToInt

fun Weather.toUi(): WeatherUi {
    val dailyUi = daily.map { it.toUi(timezoneOffset) }

    return WeatherUi(
        timezone = timezone,
        timezoneOffset = timezoneOffset,
        today = dailyUi.first(),
        hourly = hourly.map { it.toUi(timezoneOffset) },
        daily = dailyUi
    )
}

fun Daily.toUi(timezoneOffset: Int): DailyUi {
    val weather = weather.firstOrNull()

    return DailyUi(
        dayOfWeek = dt.toDayShort(timezoneOffset),
        dt = dt,

        sunrise = sunrise.toTime(timezoneOffset),
        sunset = sunset.toTime(timezoneOffset),

        moonrise = moonrise,
        moonset = moonset,
        moonPhase = moonPhase,

        summary = summary,

        temperature = mapTempsToTemp(temp.morn, temp.day, temp.eve, temp.night, timezoneOffset).roundToInt(),
        tempMin = temp.min.roundToInt(),
        tempMax = temp.max.roundToInt(),

        feelsLike = mapFeelsLikeByTime(feelsLike, timezoneOffset).roundToInt(),

        pressure = pressure,
        humidity = humidity,
        dewPoint = dewPoint,

        windSpeed = windSpeed.roundToInt(),
        windDeg = windDeg,
        windGust = windGust?.roundToInt() ?: 0,

        description = weather?.description.orEmpty(),
        iconRes = mapIconToRes(weather?.icon.orEmpty()),

        clouds = clouds,
        pop = pop,
        uvi = uvi
    )
}

fun Hourly.toUi(timezoneOffset: Int): HourlyUi {
    return HourlyUi(
        hour = dt.toHour(timezoneOffset),
        temp = temp.roundToInt(),
        iconRes = mapIconToRes(icon)
    )
}

private val iconMap = mapOf(
    "01d" to R.drawable.ic_sun,
    "01n" to R.drawable.ic_moon_2,
    "02d" to R.drawable.ic_cloud,
    "02n" to R.drawable.ic_cloud,
    "03d" to R.drawable.ic_cloud,
    "03n" to R.drawable.ic_cloud,
    "04d" to R.drawable.ic_cloud,
    "04n" to R.drawable.ic_cloud,
    "09d" to R.drawable.ic_rain,
    "09n" to R.drawable.ic_rain,
    "10d" to R.drawable.ic_rain,
    "10n" to R.drawable.ic_rain,
    "11d" to R.drawable.ic_cloud,
    "11n" to R.drawable.ic_cloud,
    "13d" to R.drawable.ic_rain,
    "13n" to R.drawable.ic_rain,
    "50d" to R.drawable.ic_wind,
    "50n" to R.drawable.ic_wind
)

fun mapIconToRes(icon: String?): Int {
    return iconMap[icon] ?: R.drawable.ic_sun
}

private val hourFormat = DateTimeFormatter.ofPattern("HH", Locale.getDefault())
private val timeFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
private val dayFormat = DateTimeFormatter.ofPattern("EEE", Locale("ru"))

fun Long.toHour(offsetSeconds: Int): String {
    return Instant.ofEpochSecond(this)
        .atOffset(ZoneOffset.ofTotalSeconds(offsetSeconds))
        .format(hourFormat)
}

fun Long.toTime(offsetSeconds: Int): String {
    return Instant.ofEpochSecond(this)
        .atOffset(ZoneOffset.ofTotalSeconds(offsetSeconds))
        .format(timeFormat)
}

fun Long.toDayShort(offsetSeconds: Int): String {
    return Instant.ofEpochSecond(this)
        .atOffset(ZoneOffset.ofTotalSeconds(offsetSeconds))
        .format(dayFormat).uppercase()
}

private fun getCurrentHourAtLocation(offsetSeconds: Int): Int {
    return Instant.now()
        .atOffset(ZoneOffset.ofTotalSeconds(offsetSeconds))
        .hour
}

fun mapFeelsLikeByTime(feelsLike: FeelsLike, timezoneOffset: Int): Double {
    val hour = getCurrentHourAtLocation(timezoneOffset)

    return when (hour) {
        in 6..11 -> feelsLike.morn     // утро
        in 12..17 -> feelsLike.day     // день
        in 18..21 -> feelsLike.eve     // вечер
        else -> feelsLike.night        // ночь
    }
}

fun mapTempsToTemp(tempMorn: Double, tempDay: Double, tempEve: Double, tempNight: Double, timezoneOffset: Int): Double {
    val hour = getCurrentHourAtLocation(timezoneOffset)

    return when (hour) {
        in 6..11 -> tempMorn     // утро
        in 12..17 -> tempDay     // день
        in 18..21 -> tempEve     // вечер
        else -> tempNight        // ночь
    }
}