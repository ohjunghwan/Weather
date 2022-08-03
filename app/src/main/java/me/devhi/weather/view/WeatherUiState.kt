package me.devhi.weather.view

data class WeatherUiState(
    val todayWeather: TodayWeatherUiState = TodayWeatherUiState(),
    val hourlyForecasts: List<HourlyForecastUiState> = listOf(),
    val dailyForecasts: List<DailyForecastUiState> = listOf(),
    val isLoading: Boolean = false,
    val address: String = "",
)

data class TodayWeatherUiState(
    val temperature: String = "",
    val summary: String = "",
    val backgroundImageUrl: String = "",
)

data class DailyForecastUiState(
    val date: String,
    val maxTemperature: String,
    val minTemperature: String,
    val iconUrl: String
)

data class HourlyForecastUiState(
    val time: String,
    val temperature: String,
    val iconUrl: String,
)