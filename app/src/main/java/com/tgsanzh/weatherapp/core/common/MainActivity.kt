package com.tgsanzh.weatherapp.core.common

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tgsanzh.weatherapp.core.navigation.PrimaryNavigation
import com.tgsanzh.weatherapp.core.ui.theme.PrimaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimaryTheme {
                PrimaryNavigation()
            }
        }
    }
}