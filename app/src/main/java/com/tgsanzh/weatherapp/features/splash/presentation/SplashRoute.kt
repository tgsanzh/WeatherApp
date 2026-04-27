package com.tgsanzh.weatherapp.features.splash.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tgsanzh.weatherapp.core.navigation.Destination
import com.tgsanzh.weatherapp.core.ui.theme.primaryColors

@Composable
fun SplashRoute(navController: NavController) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            navController.navigate(Destination.HOME.route) {
                popUpTo(0)
            }
        }
    )
    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = primaryColors.backgroundGradientBottom)
    )
}