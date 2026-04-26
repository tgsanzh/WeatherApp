package com.tgsanzh.weatherapp.features.home.presentation

sealed interface HomeEffect {
    data class ShowSnackbar(val value: String): HomeEffect
}