package me.devhi.weather.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import me.devhi.weather.dto.HourlyWeatherDTO

@ProvidedTypeConverter
class HourWeatherListTypeConverter(private val gson: Gson) {
    @TypeConverter
    fun listToJson(value: List<HourlyWeatherDTO>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<HourlyWeatherDTO> {
        return gson.fromJson(value, Array<HourlyWeatherDTO>::class.java).toList()
    }
}