package com.tgsanzh.weatherapp.features.home.presentation

sealed class HomeEvent {
    object GetData: HomeEvent()
    object NavigateToLocations: HomeEvent()
}