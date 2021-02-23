package com.kakadurf.hw_sem2.model.services

import com.kakadurf.hw_sem2.model.data.MessageHttp
import com.kakadurf.hw_sem2.model.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpService {
    @GET("data/2.5/find")
     abstract suspend fun getLocalSpots(@Query("lat") latitude : Double,
                                        @Query("lon") longitude : Double,
                                        @Query("cnt") cnt: Int ): MessageHttp
    @GET("data/2.5/weather")
    suspend fun getSpot(@Query("q") cityName: String ): WeatherResponse
    @GET("data/2.5/weather")
    suspend fun getSpotById(@Query("id") id: Int): WeatherResponse
}