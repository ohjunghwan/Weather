package me.devhi.weather.dto

import androidx.room.Entity

@Entity(tableName = "weather", primaryKeys = ["lat", "lon", "date"])
data class WeatherResponse(
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    val timezone: String = "",
    val current: HourlyWeatherDTO = HourlyWeatherDTO(),
    val hourly: List<HourlyWeatherDTO> = listOf(),
    val daily: List<DailyWeatherDTO> = listOf(),
    var date: String = ""
)

data class WeatherMainData(
    val id: Int = 0,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null,
)

data class HourlyWeatherDTO(
    val dt: Long = 0L,
    val temp: Float = 0f,
    val feels_like: Float = 0f,
    val uvi: Float = 0f,
    val clouds: Int = 0,
    val wind_speed: Float = 0f,
    val weather: List<WeatherMainData>? = null,
    val rain: DailyRainData? = null,
)

data class DailyWeatherDTO(
    val dt: Long = 0L,
    val temp: DailyTemp? = null,
    val feels_like: DailyTemp? = null,
    val uvi: Float = 0f,
    val clouds: Int = 0,
    val wind_speed: Float = 0f,
    val weather: List<WeatherMainData>? = null,
    val rain: Float? = null,
)

data class DailyRainData(
    val hourly: Float = 0f,
)

data class DailyTemp(
    val day: Float = 0f,
    val min: Float = 0f,
    val max: Float = 0f,
    val night: Float = 0f,
    val eve: Float = 0f,
    val morn: Float = 0f,
)