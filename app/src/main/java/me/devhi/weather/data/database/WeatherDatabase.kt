package me.devhi.weather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.devhi.weather.data.database.converter.DayWeatherTypeConverter
import me.devhi.weather.data.database.converter.HourWeatherListTypeConverter
import me.devhi.weather.data.database.converter.HourWeatherTypeConverter
import me.devhi.weather.dto.WeatherResponse


@Database(entities = [WeatherResponse::class], version = 1, exportSchema = false)
@TypeConverters(
    value = [
        HourWeatherListTypeConverter::class,
        DayWeatherTypeConverter::class,
        HourWeatherTypeConverter::class
    ]
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDAO(): WeatherDao
}