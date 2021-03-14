package com.kakadurf.hw_sem2.presentation.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kakadurf.hw_sem2.R
import com.kakadurf.hw_sem2.domain.WeatherResponse
import com.kakadurf.hw_sem2.domain.WindDirection
import com.kakadurf.hw_sem2.presentation.presenters.ExtendedScreenProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import kotlin.coroutines.CoroutineContext

class ExtendedWeatherScreenFragment private constructor():  MvpAppCompatFragment(), CoroutineScope,
    ExtendedMvpView {
    companion object{
        fun create(id: Int): ExtendedWeatherScreenFragment {
            return ExtendedWeatherScreenFragment()
                .apply {
                arguments = Bundle().apply {putInt("id",id)}
            }
        }
    }
    override val coroutineContext: CoroutineContext = Dispatchers.IO
    @InjectPresenter
    lateinit var presenter: ExtendedScreenProvider
    private var cityId: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_extended_weather,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityId = arguments?.getInt("id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityId?.let {
            presenter.getWeather(it)

        }
    }
    override fun inflateWeatherData(weatherResponse: WeatherResponse){
        activity?.runOnUiThread {
            weatherResponse.run {
                tv_big_tempr.text = "${mainThing.temp} celsius"
                tv_feels_like.text = "feels like: ${mainThing.feelsLike} celsius"
                tv_city_name.text = "city: $name"
                tv_humidity.text = "humidity: ${mainThing.humidity}"
                tv_wind_data.text =
                    "wind direction: ${WindDirection.create(wind.deg)
                        .toString().toLowerCase()}"
                tv_pressure.text =
                    "pressure: ${mainThing.pressure}"
            }
        }
    }
}