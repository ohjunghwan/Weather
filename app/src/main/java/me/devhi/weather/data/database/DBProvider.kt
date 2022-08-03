package me.devhi.weather.data.database

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import me.devhi.weather.data.database.converter.DayWeatherTypeConverter
import me.devhi.weather.data.database.converter.HourWeatherListTypeConverter
import me.devhi.weather.data.database.converter.HourWeatherTypeConverter

object DBProvider {
    fun provideDataBase(context: Context, gson: Gson): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "weather_db"
        ).addTypeConverter(HourWeatherListTypeConverter(gson))
            .addTypeConverter(DayWeatherTypeConverter(gson))
            .addTypeConverter(HourWeatherTypeConverter(gson))
            .build()
    }
}

