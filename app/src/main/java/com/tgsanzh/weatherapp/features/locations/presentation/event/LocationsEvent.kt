package com.tgsanzh.weatherapp.features.locations.presentation.event

sealed class LocationsEvent {
    data object NavigateToHome: LocationsEvent()
    data class BottomSheetVisibilityChanged(val value: Boolean): LocationsEvent()
    data class OnSearchTextChanged(val value: String): LocationsEvent()
}