package com.tgsanzh.weatherapp.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgsanzh.weatherapp.core.error.AppError
import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.usecases.GetWeatherUseCase
import com.tgsanzh.weatherapp.features.home.presentation.mappers.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        observeWeather()
    }

    fun dispatch(event: HomeEvent) {
        when (event) {
            HomeEvent.GetData -> {
                refresh()
            }

            HomeEvent.NoGpsPermission -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = AppError.Location.NoPermission
                )
            }
        }
    }

    private fun observeWeather() {
        viewModelScope.launch {
            getWeatherUseCase.observeWeather()
                .collect { weather ->
                    when (weather) {
                        is AppResult.Error -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = weather.error
                            )
                        }
                        is AppResult.Success<Weather> -> {
                            _state.value = _state.value.copy(
                                weather = weather.data.toUi(),
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            when (val result = getWeatherUseCase.refresh()) {
                is AppResult.Error -> {
                    _state.value = _state.value.copy(
                        error = result.error,
                        isLoading = false
                    )
                }
                is AppResult.Success -> {
                    _state.value = _state.value.copy(
                        error = null,
                        isLoading = false
                    )
                }
            }
        }
    }
}