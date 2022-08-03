package me.devhi.weather.api

import me.devhi.weather.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "055effba64cf83f8696f3d4bd1a8bbf7"

interface WeatherApi {
    @GET("onecall")
    suspend fun getWeatherInfo(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String = "metric",
        @Query("appid") apiKey: String = API_KEY,
        @Query("lang") language: String = "kr",
    ): WeatherResponse
}