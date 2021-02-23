/*
package com.kakadurf.myapplication

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext


const val PERMISSION_CODE = 676
const val BASE_URL = "https://api.openweathermap.org/"
const val API_KEY = "2cea9ac47a26395b789f5a1869c722da"
class MainActivity : AppCompatActivity(){
     val coroutineContext: CoroutineContext = Dispatchers.IO
    fun requestPerm() {

        //val perms = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
        */
/*
        applicationContext.checkSelfPermission(ACCESS_FINE_LOCATION)*//*
*/
/*
        requestPermissions(perms,PERMISSION_CODE)
        onRequestPermissionsResult(PERMISSION_CODE, perms, IntArray(PERMISSION_DENIED) )
        //ok I don't care*//*

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION
                ),
                PERMISSION_CODE
            )
        } else {
            // already permission granted
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (!(grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = null
    }

    private var wayLatitude: Double? = null
    private var wayLongitude: Double? = null
    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        requestPerm()
        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        mFusedLocationClient.lastLocation.addOnSuccessListener(this) { location: Location? ->
            wayLatitude = location?.latitude
            wayLongitude = location?.longitude
            Toast.makeText(this, "coords: ${wayLatitude} and  ${wayLongitude}", Toast.LENGTH_SHORT).show()
            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(HttpService::class.java)
            launch {
                val str = service.getLocalSpots(wayLatitude!!, wayLongitude!!, 10, API_KEY).string()
                Toast.makeText(applicationContext,
                    str,
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}*/
