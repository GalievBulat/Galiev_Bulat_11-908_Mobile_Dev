package com.kakadurf.hw_sem2.presentation.controllers

import com.kakadurf.hw_sem2.domain.WeatherResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ExtendedMvpView: MvpView {
    fun inflateWeatherData(weatherResponse: WeatherResponse)
}