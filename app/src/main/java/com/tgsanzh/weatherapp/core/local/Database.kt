package com.tgsanzh.weatherapp.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tgsanzh.weatherapp.features.home.data.local.dao.WeatherDao
import com.tgsanzh.weatherapp.features.home.data.local.entities.WeatherCacheEntity
import com.tgsanzh.weatherapp.features.home.data.local.mappers.WeatherConverter

@Database(
    entities = [WeatherCacheEntity::class],
    version = 1
)
@TypeConverters(WeatherConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}