package com.tgsanzh.weatherapp.core.navigation

sealed class Destination (val route: String) {
    data object SPLASH: Destination("splash")
    data object HOME: Destination("home")
    data object LOCATIONS: Destination("locations")
}
