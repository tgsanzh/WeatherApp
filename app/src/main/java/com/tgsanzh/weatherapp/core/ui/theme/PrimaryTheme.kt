package com.tgsanzh.weatherapp.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun PrimaryTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides primaryColors,
        LocalTypography provides primaryTypography,
        LocalShapes provides primaryShapes,
    ) {
        content()
    }
}

internal val LocalColors = staticCompositionLocalOf { primaryColors }
internal val LocalTypography = staticCompositionLocalOf { primaryTypography }
internal val LocalShapes = staticCompositionLocalOf { primaryShapes }
