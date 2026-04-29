package com.tgsanzh.weatherapp.core.ui.theme

import androidx.compose.ui.graphics.Color

data class PrimaryColors(
    val backgroundGradientTop: Color,
    val backgroundGradientBottom: Color,
    val backgroundBlue: Color,

    val card: Color,
    val cardBorder: Color,

    val bottomSheet: Color,

    val whiteBorder: Color,

    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,

    val accentBlue: Color,
    val accentBlueSecondary: Color,
    val accentGreen: Color
)

val primaryColors = PrimaryColors(
    backgroundGradientTop = Color(0xFF6FB1FF),
    backgroundGradientBottom = Color(0xFF2C5EA8),
    backgroundBlue = Color(0xFF2B4F73),

    card = Color(0xFF377DC3).copy(alpha = 1f),
    cardBorder = Color.White.copy(alpha = 0.2f),

    bottomSheet = Color(0xFF424D58).copy(alpha = 0.52f),

    whiteBorder = Color.White.copy(alpha = 0.52f),

    textPrimary = Color.White,
    textSecondary = Color(0xFFC9FFFA),
    textTertiary = Color.White.copy(alpha = 0.5f),

    accentBlue = Color(0xFF99B4D0).copy(alpha = 0.6f),
    accentBlueSecondary = Color(0xFF1F3F5F).copy(alpha = 0.6f),
    accentGreen = Color(0xFF6EE7B7)
)