package com.kakadurf.hw_sem2.data.http

import com.google.gson.annotations.SerializedName
import com.kakadurf.hw_sem2.domain.WeatherResponse

data class MessageHttp(@SerializedName("list")
                       var spots: List<WeatherResponse>)