package com.tgsanzh.weatherapp.features.locations.presentation.state

data class LocationsUiState(
    val locations: List<Location> = listOf(
        Location(
            cityName = "Almaty",
            localTime = "12:34",
            description = "В основном солнечно",
            temperature = 12,
            minTemp = 18,
            maxTemp = 4
        ),
        Location(
            cityName = "Astana",
            localTime = "12:34",
            description = "В основном солнечно",
            temperature = 5,
            minTemp = 13,
            maxTemp = -2
        )
    ),
    val showSheet: Boolean = false,
    val searchText: String = "",
    val isLoading: Boolean = false,
    val error: String? = "",
)

data class Location(
    val cityName: String,
    val localTime: String,
    val description: String,
    val temperature: Int,
    val minTemp: Int,
    val maxTemp: Int,
)