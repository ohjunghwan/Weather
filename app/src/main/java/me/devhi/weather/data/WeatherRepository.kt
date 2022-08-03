package me.devhi.weather.data

import me.devhi.weather.api.WeatherApi
import me.devhi.weather.data.database.WeatherDao
import me.devhi.weather.dto.WeatherResponse
import java.text.SimpleDateFormat
import java.util.*

class WeatherRepository(
    private val remoteDateSource: WeatherApi,
    private val localDataSource: WeatherDao
) {
    suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double,
        forceReset: Boolean = false
    ): WeatherResponse {
        return if (forceReset) {
            getRemoteWeatherAndCache(latitude, longitude)
        } else {
            localDataSource.getWeather(latitude, longitude, getToday())
                ?: getRemoteWeatherAndCache(latitude, longitude)
        }
    }

    private suspend fun getRemoteWeatherAndCache(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {
        val result = remoteDateSource.getWeatherInfo(latitude, longitude)
        localDataSource.insertWeather(result.apply { this.date = getToday() })
        return result
    }

    private fun getToday() = SimpleDateFormat("yyyy-MM-dd").format(Date())
}