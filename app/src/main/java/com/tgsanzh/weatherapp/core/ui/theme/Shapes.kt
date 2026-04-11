package com.tgsanzh.weatherapp.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class PrimaryShapes(
    val large: RoundedCornerShape,
    val medium: RoundedCornerShape,
    val small: RoundedCornerShape
)

val primaryShapes = PrimaryShapes(
    large = RoundedCornerShape(20.dp),
    medium = RoundedCornerShape(16.dp),
    small = RoundedCornerShape(12.dp)
)