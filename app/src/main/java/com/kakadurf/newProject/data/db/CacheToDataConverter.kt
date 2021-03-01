/*
package com.kakadurf.hw_sem2.data.db

import androidx.room.TypeConverter
import com.kakadurf.hw_sem2.domain.MainThing
import com.kakadurf.hw_sem2.domain.WeatherResponse
import com.kakadurf.hw_sem2.domain.Wind

class CacheToDataConverter {
    @TypeConverter
    fun mapFromResponse(response: WeatherResponse): DbCachedWeather {
        with(response) {
            return DbCachedWeather(
                id, name, wind.deg,
                mainThing.temp, false,
                mainThing.feelsLike,
                mainThing.humidity, mainThing.pressure,
                mainThing.tempMax, mainThing.tempMin
            )
        }
    }
    @TypeConverter
    fun mapToResponse(response: DbCachedWeather): WeatherResponse {
        with(response) {
            return WeatherResponse(
                id, MainThing(
                    feelsLike,
                    humidity,
                    pressure,
                    temp,
                    tempMax,
                    tempMin
                ),
                name,
                Wind(windDeg)
            )
        }
    }
}*/
