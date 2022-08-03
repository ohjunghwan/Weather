package me.devhi.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.devhi.weather.data.*
import me.devhi.weather.view.WeatherUiState

class MainViewModel : ViewModel() {
    lateinit var locationProvider: LocationProvider
    lateinit var repository: WeatherRepository
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun loadWeatherData(forceReset: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            val location = locationProvider.getCurrentLocation()
            if (location != null) {
                val address = locationProvider.getAddress(location) ?: ""
                val data =
                    repository.getWeatherInfo(location.latitude, location.longitude, forceReset)
                _uiState.update {
                    it.copy(
                        address = address,
                        todayWeather = data.current.toTodayUiState(),
                        dailyForecasts = data.daily.map { it.toUiState() },
                        hourlyForecasts = data.hourly.map { it.toUiState() },
                    )
                }
            }
        }
    }

}




