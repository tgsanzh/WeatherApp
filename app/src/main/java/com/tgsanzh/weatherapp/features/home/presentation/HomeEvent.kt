package com.tgsanzh.weatherapp.features.home.presentation

sealed class HomeEvent {
    object GetData: HomeEvent()
    object NoGpsPermission: HomeEvent()
    object NavigateToLocations: HomeEvent()
}