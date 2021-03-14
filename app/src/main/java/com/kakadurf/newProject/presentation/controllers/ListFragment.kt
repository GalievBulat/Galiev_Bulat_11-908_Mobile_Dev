package com.kakadurf.hw_sem2.presentation.controllers

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakadurf.hw_sem2.R
import com.kakadurf.hw_sem2.presentation.adapters.SpotAdapter
import com.kakadurf.hw_sem2.presentation.models.SpotDTO
import com.kakadurf.hw_sem2.presentation.presenters.ListPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

const val BASE_URL = "https://api.openweathermap.org/"

class ListFragment(val onChoice: (Int)-> Unit): MvpAppCompatFragment(),
    ListMvpView {
    @InjectPresenter
    lateinit var presenter: ListPresenter
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
        activity?.let { activity->
            presenter.getLocation(activity)
        }
        searchView_main.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val text = searchView_main.query.toString()
                presenter.findSpotWeather(text)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
    override fun onDataFetch (list: List<SpotDTO>) {
        activity?.runOnUiThread {
            recyclerView_main.layoutManager = LinearLayoutManager(context)
            recyclerView_main.adapter =
                SpotAdapter(
                    list
                ) { int: Int ->
                    onChoice(int)
                    Log.d("hi", int.toString())
                }
        }
    }
    override fun onSearchDataFetch (id: Int) {
        onChoice(id)
    }
    override fun checkPerms(){
        activity?.let { activity->
            if (ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                    .checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("hi", "error")
            }
        }
    }
}
