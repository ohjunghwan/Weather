package me.devhi.weather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.devhi.weather.dto.WeatherResponse

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather WHERE lat=:lat AND lon=:lon AND date=:date")
    fun getWeather(lat: Double, lon: Double, date: String): WeatherResponse?

    @Query("SELECT count(*) FROM weather WHERE lat=:lat AND lon=:lon AND date=:date")
    fun getCachedCount(lat: Double, lon: Double, date: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherResponse: WeatherResponse)
}