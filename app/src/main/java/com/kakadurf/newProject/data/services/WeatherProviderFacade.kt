package com.kakadurf.hw_sem2.data.services

import android.util.Log
import com.kakadurf.hw_sem2.API_KEY
import com.kakadurf.hw_sem2.presentation.controllers.BASE_URL
import com.kakadurf.hw_sem2.data.db.DataBase
import com.kakadurf.hw_sem2.domain.WeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import com.kakadurf.hw_sem2.data.db.DbCachedWeather.Companion.mapFromResponse
import com.kakadurf.hw_sem2.data.db.DbCachedWeather.Companion.mapToResponse
import com.kakadurf.hw_sem2.data.http.HttpService

//NO DAGGER(
const val API_QUERY = "appid"
const val UNITS_QUERY = "units"
const val UNITS_VALUE = "metric"
object WeatherProviderFacade{
    private fun Interceptor.Chain.addQuery(key: String, value: String): okhttp3.Response =
        request().url().newBuilder().addQueryParameter(key, value).build().let {
            proceed(request().newBuilder().url(it).build())
        }
    private val apiKeyInterceptor = Interceptor{ it.addQuery(API_QUERY, API_KEY) }
    private val metricInterceptor = Interceptor{ it.addQuery(
        UNITS_QUERY,
        UNITS_VALUE
    ) }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(metricInterceptor)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: HttpService = retrofit.create(
        HttpService::class.java)
    private var db : DataBase? = null
    //ВЫНЕСТИ БД В КЛАСС
    fun injectDb(db: DataBase){
        WeatherProviderFacade.db = db
    }
    suspend fun getLocalWeatherList(latitude: Double, longitude: Double): List<WeatherResponse>{
        return try {
            val spots = service.getLocalSpots(latitude, longitude, 10).spots
            spots.let {
                db?.run {
                    dao().updateLocalSpots(it.map { mapFromResponse(it) })
                }
            }
            spots
        } catch (e: IOException){
            Log.d("hi", "error")
            db?.run {
                dao().getSavedLocalSpots().map { mapToResponse(it) }
            } ?: emptyList()
        }
    }
    suspend fun getSpecificWeather(cityName: String): WeatherResponse?{
        return try {
            if (cityName.isNotEmpty())
                service.getSpot(cityName).also {
                    db?.run {
                        dao().insertWeather(mapFromResponse(it))
                    }
                }
            else
                null
            } catch (e: IOException){
                Log.d("hi", "error")
                db?.run {
                    dao().getSavedSpotByName(cityName)
                }?.let { mapToResponse(it) }
        }
    }
    suspend fun getSpecificWeather(cityId: Int): WeatherResponse?{
        return try {
                service.getSpotById(cityId).also {
                    db?.run {
                        dao().insertWeather(mapFromResponse(it))
                    }
                }
        } catch (e: IOException){
            db?.run {
                mapToResponse(dao().getSavedSpotById(cityId))
            }
        }
    }
}
