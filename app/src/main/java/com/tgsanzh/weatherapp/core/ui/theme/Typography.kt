package com.tgsanzh.weatherapp.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class PrimaryTypography(
    val temperature: TextStyle,
    val temperatureSmall: TextStyle,
    val bottomSheetText: TextStyle,
    val city: TextStyle,
    val citySmall: TextStyle,
    val header: TextStyle,
    val description: TextStyle,
    val descriptionSmall: TextStyle,
    val boundaries: TextStyle,
    val boundariesSmall: TextStyle,
    val body: TextStyle,
    val bodyNormal: TextStyle,
    val bodyTransparent: TextStyle,
    val caption: TextStyle
)

val primaryTypography = PrimaryTypography(
    temperature = TextStyle(
        fontSize = 64.sp,
        fontWeight = FontWeight.Light,
        color = primaryColors.textPrimary,
    ),

    temperatureSmall = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Light,
        color = primaryColors.textPrimary,
    ),

    bottomSheetText = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = primaryColors.textPrimary,
    ),

    city = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Medium,
        color = primaryColors.textPrimary,
    ),

    citySmall = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        color = primaryColors.textPrimary,
    ),

    header = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        color = primaryColors.textPrimary,
    ),

    description = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        color = primaryColors.textSecondary,
    ),

    descriptionSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = primaryColors.textSecondary,
    ),

    boundaries = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        color = primaryColors.textPrimary,
    ),

    boundariesSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = primaryColors.textPrimary,
    ),

    body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = primaryColors.textPrimary,
    ),

    bodyNormal = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = primaryColors.textPrimary,
    ),

    bodyTransparent = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = primaryColors.textTertiary,
    ),

    caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = primaryColors.textPrimary,
    )
)