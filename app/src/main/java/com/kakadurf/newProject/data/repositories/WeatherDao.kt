package com.kakadurf.hw_sem2.data.repositories

import androidx.room.*
import com.kakadurf.hw_sem2.data.db.DbCachedWeather
import com.kakadurf.hw_sem2.domain.WeatherResponse

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(vararg cachedWeather : DbCachedWeather)
    @Query("SELECT * FROM cache WHERE is_local = 1")
    fun getSavedLocalSpots(): List<DbCachedWeather>
    @Query("DELETE FROM cache WHERE is_local = 1")
    fun clearLocalSpots()
    @Query("SELECT * FROM cache WHERE name = :name")
    fun getSavedSpotByName(name: String): DbCachedWeather
    @Query("SELECT * FROM cache WHERE id = :id")
    fun getSavedSpotById(id: Int): DbCachedWeather
    @Transaction
    fun updateLocalSpots(list: List<DbCachedWeather>){
        clearLocalSpots()
        list.forEach {
            it.isLocal = true
        }
        insertWeather(*list.toTypedArray())
    }
}