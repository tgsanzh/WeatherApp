package com.tgsanzh.weatherapp.core.ui.theme

import androidx.compose.ui.graphics.Color

data class PrimaryColors(
    val backgroundGradientTop: Color,
    val backgroundGradientBottom: Color,

    val card: Color,
    val cardBorder: Color,

    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,

    val accentBlue: Color,
    val accentGreen: Color
)

val primaryColors = PrimaryColors(
    backgroundGradientTop = Color(0xFF6FB1FF),
    backgroundGradientBottom = Color(0xFF2C5EA8),

    card = Color(0xFF377DC3).copy(alpha = 0.6f),
    cardBorder = Color.White.copy(alpha = 0.2f),

    textPrimary = Color.White,
    textSecondary = Color(0xFFC9FFFA),
    textTertiary = Color.White.copy(alpha = 0.5f),

    accentBlue = Color(0xFF60A5FA),
    accentGreen = Color(0xFF6EE7B7)
)