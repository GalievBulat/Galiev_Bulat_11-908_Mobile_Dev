package com.kakadurf.hw_sem2.data.db

import com.kakadurf.hw_sem2.domain.WeatherResponse

class CacheWorkingService(private val db: DataBase) {
    suspend fun getLocalWeatherFromCache(): List<WeatherResponse>{
        return db.dao().getSavedLocalSpots().map { DbCachedWeather.mapToResponse(it) }
    }
    suspend fun putWeatherInCache(list: List<WeatherResponse>){
        db.dao().updateLocalSpots(list.map { DbCachedWeather.mapFromResponse(it) })
    }
    suspend fun getFromCache(id: Int): WeatherResponse?{
        return db.run {
            DbCachedWeather.mapToResponse(dao().getSavedSpotById(id))
        }
    }
    suspend fun getFromCache(name: String): WeatherResponse? {
        return db.
            dao().getSavedSpotByName(name).let { DbCachedWeather.mapToResponse(it) }
    }
    suspend fun saveToCache(response: WeatherResponse){
        db.dao().insertWeather(DbCachedWeather.mapFromResponse(response))
    }
}