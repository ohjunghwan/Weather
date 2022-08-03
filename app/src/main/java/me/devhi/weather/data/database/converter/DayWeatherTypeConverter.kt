package me.devhi.weather.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import me.devhi.weather.dto.DailyWeatherDTO

@ProvidedTypeConverter
class DayWeatherTypeConverter(private val gson: Gson) {
    @TypeConverter
    fun listToJson(value: List<DailyWeatherDTO>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<DailyWeatherDTO> {
        return gson.fromJson(value, Array<DailyWeatherDTO>::class.java).toList()
    }
}