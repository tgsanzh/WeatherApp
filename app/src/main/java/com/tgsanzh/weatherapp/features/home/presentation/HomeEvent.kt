package com.tgsanzh.weatherapp.features.home.presentation

sealed class HomeEvent {
    object GetLocation: HomeEvent()
}