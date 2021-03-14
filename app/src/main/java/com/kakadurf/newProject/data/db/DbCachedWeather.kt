package com.kakadurf.hw_sem2.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kakadurf.hw_sem2.domain.MainThing
import com.kakadurf.hw_sem2.domain.WeatherResponse
import com.kakadurf.hw_sem2.domain.Wind

@Entity(tableName = "cache")
data class DbCachedWeather (
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Int,
        @ColumnInfo(name = "name")
        var name: String,
        @ColumnInfo(name = "wind_deg")
        var windDeg: Int,
        @ColumnInfo(name = "temp")
        var temp: Double,
        @ColumnInfo(name = "is_local")
        var isLocal: Boolean = false,
        @ColumnInfo(name = "feels_like")
        var feelsLike: Double,
        @ColumnInfo(name = "humidity")
        var humidity: Int,
        @ColumnInfo(name = "pressure")
        var pressure: Int,
        @ColumnInfo(name = "temp_max")
        var tempMax: Double,
        @ColumnInfo(name = "temp_min")
        var tempMin: Double

) {
    companion object{
        fun mapFromResponse(response: WeatherResponse, isLocal: Boolean = false): DbCachedWeather {
                with(response) {
                        return DbCachedWeather(
                            id, name, wind.deg,
                            mainThing.temp, isLocal, mainThing.feelsLike,
                            mainThing.humidity, mainThing.pressure,
                            mainThing.tempMax, mainThing.tempMin
                        )
                }
        }
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
    }
}
