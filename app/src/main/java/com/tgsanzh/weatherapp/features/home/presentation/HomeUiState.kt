package com.tgsanzh.weatherapp.features.home.presentation

import com.tgsanzh.weatherapp.core.location.domain.models.Location

data class HomeUiState(
    val location: Location? = null,
    val errorText: String? = null,
    val isLoading: Boolean = false,
)