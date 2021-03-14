package com.kakadurf.hw_sem2.presentation.presenters

import com.kakadurf.hw_sem2.data.services.WeatherProviderFacade
import com.kakadurf.hw_sem2.presentation.controllers.ExtendedMvpView
import kotlinx.coroutines.Dispatchers
import moxy.MvpPresenter
import moxy.presenterScope

class ExtendedScreenProvider: MvpPresenter<ExtendedMvpView>() {
    fun getWeather(id: Int){
        presenterScope.launch(Dispatchers.IO) {
            val weatherResponse =
                WeatherProviderFacade.getSpecificWeather(
                    id
                )
            weatherResponse?.let {
                viewState.inflateWeatherData(it) }
        }
    }
}