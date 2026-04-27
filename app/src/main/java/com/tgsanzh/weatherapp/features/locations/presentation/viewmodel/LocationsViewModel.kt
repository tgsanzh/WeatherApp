package com.tgsanzh.weatherapp.features.locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.tgsanzh.weatherapp.features.locations.presentation.effect.LocationsEffect
import com.tgsanzh.weatherapp.features.locations.presentation.event.LocationsEvent
import com.tgsanzh.weatherapp.features.locations.presentation.state.LocationsUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationsViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(LocationsUiState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LocationsEffect>()
    val effect = _effect.asSharedFlow()

    fun dispatch(event: LocationsEvent) {
        when (event) {
            LocationsEvent.NavigateToHome -> {

            }
            is LocationsEvent.BottomSheetVisibilityChanged -> {
                _state.value = _state.value.copy(showSheet = event.value)
            }

            is LocationsEvent.OnSearchTextChanged -> {
                _state.value = _state.value.copy(searchText = event.value)
            }
        }
    }
}