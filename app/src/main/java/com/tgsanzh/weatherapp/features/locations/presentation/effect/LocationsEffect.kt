package com.tgsanzh.weatherapp.features.locations.presentation.effect

sealed interface LocationsEffect {
    data object NavigateToHome: LocationsEffect
}