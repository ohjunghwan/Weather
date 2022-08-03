package me.devhi.weather.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import me.devhi.weather.dto.HourlyWeatherDTO

@ProvidedTypeConverter
class HourWeatherTypeConverter(private val gson: Gson) {
    @TypeConverter
    fun listToJson(value: HourlyWeatherDTO): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): HourlyWeatherDTO {
        return gson.fromJson(value, HourlyWeatherDTO::class.java)
    }
}