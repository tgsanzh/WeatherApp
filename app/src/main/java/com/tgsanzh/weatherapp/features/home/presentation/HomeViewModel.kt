package com.tgsanzh.weatherapp.features.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgsanzh.weatherapp.features.home.domain.usecases.GetLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    val getLocationUseCase: GetLocationUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun dispatch(event: HomeEvent) {
        when(event) {
            HomeEvent.GetLocation -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(isLoading = true)

                    val result = getLocationUseCase.getLocation()
                    if (result != null)
                    {
                        _state.value = _state.value.copy(location = result)
                        Log.d("Location: ", result.toString())
                    }
                    else {
                        Log.d("Location: ", "Null")
                    }

                    _state.value = _state.value.copy(isLoading = false)
                }
            }
        }
    }
}