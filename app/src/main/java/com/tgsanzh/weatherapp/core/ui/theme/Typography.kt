package com.tgsanzh.weatherapp.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class PrimaryTypography(
    val temperature: TextStyle,
    val city: TextStyle,
    val description: TextStyle,
    val body: TextStyle,
    val caption: TextStyle
)

val primaryTypography = PrimaryTypography(
    temperature = TextStyle(
        fontSize = 64.sp,
        fontWeight = FontWeight.Light
    ),

    city = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium
    ),

    description = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),

    body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),

    caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
)