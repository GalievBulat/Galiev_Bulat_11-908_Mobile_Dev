package com.kakadurf.hw_sem2.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kakadurf.hw_sem2.R
import com.kakadurf.hw_sem2.model.data.WindDirection
import com.kakadurf.hw_sem2.model.services.WeatherProviderFacade
import kotlinx.android.synthetic.main.fragment_extended_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ExtendedWeatherScreenFragment: Fragment(), CoroutineScope {
    companion object{
        fun create(id: Int): ExtendedWeatherScreenFragment {
            return ExtendedWeatherScreenFragment()
                .apply {
                arguments = Bundle().apply {putInt("id",id)}
            }
        }
    }
    override val coroutineContext: CoroutineContext = Dispatchers.IO
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
            launch {
                val weatherResponse =
                    WeatherProviderFacade.getSpecificWeatherById(
                        it
                    )
                activity?.runOnUiThread {
                    tv_big_tempr.text = "${weatherResponse.mainThing.temp} celsius"
                    tv_city_name.text = "city: ${weatherResponse.name}"
                    tv_humidity.text = "humidity: ${weatherResponse.mainThing.humidity}"
                    tv_wind_data.text = "wind direction: ${WindDirection.
                    create(weatherResponse.wind.deg).toString()}"
                }
            }
        }
    }
}