package com.kakadurf.hw_sem2.presentation.presenters

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.kakadurf.hw_sem2.data.services.WeatherProviderFacade
import com.kakadurf.hw_sem2.presentation.controllers.ListMvpView
import com.kakadurf.hw_sem2.presentation.models.SpotDTO
import kotlinx.coroutines.Dispatchers
import moxy.MvpPresenter
import moxy.presenterScope

class ListPresenter: MvpPresenter<ListMvpView>() {

    private fun findSpot(lt: Double,
                         ln: Double){
        presenterScope.launch(Dispatchers.IO) {
            val str =
                WeatherProviderFacade.getLocalWeatherList(lt, ln)
            Log.d("hi", "got spots")
            val dtoSet: ArrayList<SpotDTO> = ArrayList()
            str.forEach { weather ->
                dtoSet.add(
                    SpotDTO(
                        weather.id,
                        weather.name,
                        weather.mainThing.temp
                    )
                )
            }
            viewState.onDataFetch(dtoSet)
        }
    }
    fun findSpotWeather(text: String){
        presenterScope.launch(Dispatchers.IO) {
            WeatherProviderFacade.getSpecificWeather(text)?.id?.let {
                viewState.onSearchDataFetch(it)
            }
        }
    }
    @SuppressLint("MissingPermission")
    fun getLocation(context: Activity){
         viewState.checkPerms()
         val mFusedLocationClient =
             LocationServices.getFusedLocationProviderClient(context)
         mFusedLocationClient.lastLocation.addOnSuccessListener(context) { location: Location ->
             val wayLatitude = location.latitude
             val wayLongitude = location.longitude
             Log.d("hi", "coords: $wayLatitude and  $wayLongitude")
             findSpot(wayLatitude, wayLongitude)
         }

    }
}