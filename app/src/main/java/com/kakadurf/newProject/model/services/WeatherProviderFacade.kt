package com.kakadurf.hw_sem2.model.services

import com.kakadurf.hw_sem2.API_KEY
import com.kakadurf.hw_sem2.controllers.BASE_URL
import com.kakadurf.hw_sem2.model.data.WeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WeatherProviderFacade{
    private fun Interceptor.Chain.addQuery(key: String, value: String): okhttp3.Response =
        request().url().newBuilder().addQueryParameter(key, value).build().let {
            proceed(request().newBuilder().url(it).build())
        }
    private val apiKeyInterceptor = Interceptor{ it.addQuery("appid", API_KEY) }
    private val metricInterceptor = Interceptor{ it.addQuery("units", "metric") }
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(metricInterceptor)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service: HttpService = retrofit.create(HttpService::class.java)
    suspend fun getLocalWeatherList(latitude: Double, longitude: Double): List<WeatherResponse>{
        return service.getLocalSpots(latitude , longitude, 10).spots
    }
    suspend fun getSpecificWeather(cityName: String): WeatherResponse?{
        return if (cityName.isNotEmpty())
            service.getSpot(cityName)
        else
            null
    }
    suspend fun getSpecificWeatherById(id: Int): WeatherResponse {
        return service.getSpotById(id)
    }
}
