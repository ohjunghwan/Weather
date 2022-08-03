package me.devhi.weather.data

import me.devhi.weather.view.DailyForecastUiState
import me.devhi.weather.view.HourlyForecastUiState
import me.devhi.weather.view.TodayWeatherUiState
import me.devhi.weather.dto.DailyWeatherDTO
import me.devhi.weather.dto.HourlyWeatherDTO
import me.devhi.weather.dto.WeatherMainData
import java.text.SimpleDateFormat
import java.util.*

fun HourlyWeatherDTO.toTodayUiState() = TodayWeatherUiState(
    temperature = "${this.temp.toInt()}°C",
    summary = "${this.weather?.firstOrNull()?.description ?: ""}, 체감온도 ${this.feels_like}°C",
    backgroundImageUrl = this.weather.getBackgroundUrl()
)

fun DailyWeatherDTO.toUiState() = DailyForecastUiState(
    date = dailyFormat.format(this.dt * 1000),
    maxTemperature = "${this.temp?.max?.toInt()}°C",
    minTemperature = "${this.temp?.min?.toInt()}°C",
    iconUrl = this.weather.getIconUrl()
)

fun HourlyWeatherDTO.toUiState() = HourlyForecastUiState(
    time = hourlyFormat.format(this.dt * 1000),
    temperature = "${this.temp.toInt()}°C",
    iconUrl = this.weather.getIconUrl()
)


private fun List<WeatherMainData>?.getIconUrl(): String {
    return this?.firstOrNull()?.icon?.let {
        "https://openweathermap.org/img/wn/${it}@2x.png"
    } ?: ""
}

private fun List<WeatherMainData>?.getBackgroundUrl(): String {
    return this?.firstOrNull()?.icon?.let {
        "https://zs.zp.ua/weather/png/win_${it}.png.png"
    } ?: ""
}


private val dailyFormat = SimpleDateFormat("E요일", Locale.KOREAN)
private val hourlyFormat = SimpleDateFormat("aa h시 ", Locale.KOREAN)
