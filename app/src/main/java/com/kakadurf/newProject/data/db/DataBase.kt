package com.kakadurf.hw_sem2.data.db

import android.content.Context
import androidx.room.*
import com.kakadurf.hw_sem2.data.repositories.WeatherDao

const val DATABASE_NAME = "com.kakadurf.sem_2.weather.db"

@Database(entities = [DbCachedWeather::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun dao(): WeatherDao
    companion object {
        private lateinit var sInstance: DataBase
        @Synchronized
        fun getInstance(context: Context): DataBase {
            if (!Companion::sInstance.isInitialized) {
                sInstance = Room.databaseBuilder(context, DataBase::class.java,
                    DATABASE_NAME
                ).
                build()
            }
            return sInstance
        }
    }
}
