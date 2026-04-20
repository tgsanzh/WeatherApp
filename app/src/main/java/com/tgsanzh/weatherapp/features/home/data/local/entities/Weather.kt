package com.tgsanzh.weatherapp.features.home.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_cache")
data class WeatherCacheEntity(
    @PrimaryKey val id: Int = 0,
    val responseJson: String,
    val updatedAt: Long
)