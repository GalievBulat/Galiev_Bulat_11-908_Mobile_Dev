package com.kakadurf.hw_sem2.model.data

class SpotDTO(
    var id: Int,
    val name: String,
    val tempr: Double,
    val temprData: TemperatureData? = TemperatureData.create(tempr)
)