package com.kakadurf.hw_sem2.data.http

import com.kakadurf.hw_sem2.domain.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpService {
    @GET("data/2.5/find")
    suspend fun getLocalSpots(@Query("lat") latitude : Double,
                                        @Query("lon") longitude : Double,
                                        @Query("cnt") cnt: Int ): MessageHttp
    @GET("data/2.5/weather")
    suspend fun getSpot(@Query("q") cityName: String ): WeatherResponse
    @GET("data/2.5/weather")
    suspend fun getSpotById(@Query("id") id: Int): WeatherResponse
}