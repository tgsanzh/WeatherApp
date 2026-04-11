package com.tgsanzh.weatherapp.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun HomeRoute(navController: NavController) {
    val vm = koinViewModel<HomeViewModel>()

    HomeScreen(
        state = vm.state.collectAsState().value,
    ) {
        vm.dispatch(it)
    }
}