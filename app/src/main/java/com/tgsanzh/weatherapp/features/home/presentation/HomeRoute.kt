package com.tgsanzh.weatherapp.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import com.tgsanzh.weatherapp.core.navigation.Destination

@Composable
fun HomeRoute(navController: NavController) {
    val homeViewModel = koinViewModel<HomeViewModel>()

    LaunchedEffect(Unit) {
        homeViewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.ShowSnackbar -> TODO()
                HomeEffect.NavigateToLocations -> {
                    navController.navigate(Destination.LOCATIONS.route)
                }
            }
        }
    }

    HomeScreen(
        state = homeViewModel.state.collectAsState().value,
    ) {
        homeViewModel.dispatch(it)
    }
}