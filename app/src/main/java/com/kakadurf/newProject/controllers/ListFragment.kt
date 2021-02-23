package com.kakadurf.hw_sem2.controllers

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationServices
import com.kakadurf.hw_sem2.R
import com.kakadurf.hw_sem2.model.services.SpotAdapter
import com.kakadurf.hw_sem2.model.data.SpotDTO
import com.kakadurf.hw_sem2.model.data.TemperatureData
import com.kakadurf.hw_sem2.model.services.WeatherProviderFacade
import kotlinx.android.synthetic.main.fragment_weather_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

const val BASE_URL = "https://api.openweathermap.org/"

class ListFragment(val onChoice: (Int)-> Unit): Fragment(), CoroutineScope{
    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private var wayLatitude: Double? = null
    private var wayLongitude: Double? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_main.layoutManager = LinearLayoutManager(context)
        activity?.let { activity->
            val mFusedLocationClient =
                LocationServices.getFusedLocationProviderClient(activity)
            if (ActivityCompat.
                checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                    .checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                Log.d("hi","error")
                return
            }
            mFusedLocationClient.lastLocation.addOnSuccessListener(activity) { location: Location ->
                wayLatitude = location.latitude
                wayLongitude = location.longitude
                Log.d("hi", "coords: $wayLatitude and  $wayLongitude")
                wayLatitude?.let{lt->
                    wayLongitude?.let {ln->
                        launch {
                            val str =
                                WeatherProviderFacade.getLocalWeatherList(lt, ln)
                            val dtoSet: ArrayList<SpotDTO> = ArrayList()
                            str.forEach { weather ->
                                dtoSet.add(
                                    SpotDTO(
                                        weather.id,
                                        weather.name,
                                        weather.mainThing.temp
                                    ))
                            }
                            dtoSet.forEach({})
                            activity.runOnUiThread {
                                recyclerView_main.adapter = SpotAdapter(dtoSet) { int: Int ->
                                    onChoice(int)
                                    Log.d("hi", int.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
        searchView_main.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val text = searchView_main.query.toString()
                launch {
                    WeatherProviderFacade.getSpecificWeather(text)?.id?.let {
                        onChoice(it)
                    }
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}
