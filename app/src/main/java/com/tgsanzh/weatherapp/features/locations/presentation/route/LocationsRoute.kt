package com.tgsanzh.weatherapp.features.locations.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.tgsanzh.weatherapp.features.locations.presentation.effect.LocationsEffect
import com.tgsanzh.weatherapp.features.locations.presentation.screen.LocationsScreen
import com.tgsanzh.weatherapp.features.locations.presentation.viewmodel.LocationsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsRoute(navController: NavController) {
    val locationsViewModel = koinViewModel<LocationsViewModel>()

    LaunchedEffect(Unit) {
        locationsViewModel.effect.collect { effect ->
            when (effect) {
                LocationsEffect.NavigateToHome -> {
                    navController.popBackStack()
                }
            }
        }
    }

    LocationsScreen(
        state = locationsViewModel.state.collectAsState().value,
    ) {
        locationsViewModel.dispatch(it)
    }
}