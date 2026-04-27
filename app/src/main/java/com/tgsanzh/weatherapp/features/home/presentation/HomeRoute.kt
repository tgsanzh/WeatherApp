package com.tgsanzh.weatherapp.features.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.tgsanzh.weatherapp.core.navigation.Destination

@Composable
fun HomeRoute(navController: NavController) {
    val homeViewModel = koinViewModel<HomeViewModel>()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        homeViewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message
                    )
                }
                HomeEffect.NavigateToLocations -> {
                    navController.navigate(Destination.LOCATIONS.route)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        )
        HomeScreen(
            state = homeViewModel.state.collectAsState().value,
        ) {
            homeViewModel.dispatch(it)
        }
    }
}