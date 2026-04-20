package com.tgsanzh.weatherapp.features.home.presentation.mappers

import com.tgsanzh.weatherapp.R

object WeatherBackgroundMapper {

    fun getBackgroundRes(pop: Double, hour: Int): Int {
        val isRain = pop >= 0.4

        val period = when (hour) {
            in 6..11 -> "MORN"
            in 12..17 -> "DAY"
            in 18..21 -> "EVE"
            else -> "NIGHT"
        }

        return when {
            !isRain && period == "MORN" -> R.drawable.bg_clear_morn
            !isRain && period == "DAY" -> R.drawable.bg_clear_day
            !isRain && period == "EVE" -> R.drawable.bg_clear_eve
            !isRain -> R.drawable.bg_clear_night

            isRain && period == "MORN" -> R.drawable.bg_rainly_morn
            isRain && period == "DAY" -> R.drawable.bg_rainly_day
            isRain && period == "EVE" -> R.drawable.bg_rainly_eve
            else -> R.drawable.bg_rainly_night
        }
    }
}