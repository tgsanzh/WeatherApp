package com.tgsanzh.weatherapp.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgsanzh.weatherapp.core.error.AppError
import com.tgsanzh.weatherapp.core.result.AppResult
import com.tgsanzh.weatherapp.features.home.domain.models.Weather
import com.tgsanzh.weatherapp.features.home.domain.usecases.ObserveWeatherUseCase
import com.tgsanzh.weatherapp.features.home.domain.usecases.RefreshWeatherUseCase
import com.tgsanzh.weatherapp.features.home.presentation.mappers.toUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeUseCase: ObserveWeatherUseCase,
    private val refreshUseCase: RefreshWeatherUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeEffect>()
    val effect = _effect.asSharedFlow()

    init {
        observeWeather()
    }

    fun dispatch(event: HomeEvent) {
        when (event) {
            HomeEvent.GetData -> {
                refresh()
            }

            HomeEvent.NoGpsPermission -> {
                _state.update { current ->
                    current.copy(
                        isLoading = false,
                        error = AppError.Location.NoPermission
                    )
                }
            }
        }
    }

    private fun observeWeather() {
        viewModelScope.launch {
            observeUseCase.observeWeather()
                .collect { weather ->
                    when (weather) {
                        is AppResult.Error -> {
                            _state.update { current ->
                                current.copy(
                                    isLoading = false,
                                    error = weather.error
                                )
                            }
                        }
                        is AppResult.Success<Weather> -> {
                            _state.update { current ->
                                current.copy(
                                    weather = weather.data.toUi(),
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            when (val result = refreshUseCase.refresh()) {
                is AppResult.Error -> {
                    _state.update { current ->
                        current.copy(
                            error = result.error,
                            isLoading = false
                        )
                    }
                }
                is AppResult.Success -> {
                    _state.update { current ->
                        current.copy(
                            error = null,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}