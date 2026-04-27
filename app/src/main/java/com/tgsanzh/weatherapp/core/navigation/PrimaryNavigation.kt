package com.tgsanzh.weatherapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tgsanzh.weatherapp.features.home.presentation.HomeRoute
import com.tgsanzh.weatherapp.features.locations.presentation.route.LocationsRoute
import com.tgsanzh.weatherapp.features.splash.presentation.SplashRoute

@Composable
fun PrimaryNavigation() {
    val navController = rememberNavController()
    NavHost(
        startDestination = Destination.SPLASH.route,
        navController = navController
    ) {
        composable(Destination.SPLASH.route) {
            SplashRoute(navController)
        }
        composable(Destination.HOME.route) {
            HomeRoute(navController)
        }
        composable(Destination.LOCATIONS.route) {
            LocationsRoute(navController)
        }
    }
}
