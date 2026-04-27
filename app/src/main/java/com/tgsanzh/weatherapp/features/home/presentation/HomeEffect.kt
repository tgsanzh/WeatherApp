package com.tgsanzh.weatherapp.features.home.presentation

sealed interface HomeEffect {
    data class ShowSnackbar(val message: String): HomeEffect
    data object NavigateToLocations: HomeEffect
}