package com.kakadurf.hw_sem2.presentation.models

import com.kakadurf.hw_sem2.domain.WeatherResponse

class SpotDTO(
    var id: Int,
    val name: String,
    val tempr: Double
){
    companion object{
        fun getFromResponse(response: WeatherResponse): SpotDTO {
            return SpotDTO(
                response.id,
                response.name,
                response.mainThing.temp
            )
        }
    }
}