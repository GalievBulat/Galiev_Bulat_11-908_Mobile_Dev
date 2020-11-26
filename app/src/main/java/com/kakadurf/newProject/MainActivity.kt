package com.kakadurf.newProject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Process.myPid
import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository =  MusicRepository()
        rv_main.adapter = MusicAdapter(repository.getAll())
        rv_main.layoutManager = LinearLayoutManager(this)
        val intent = Intent(this,MusicHandlingService::class.java)


        startForegroundService(intent)
        bindService(intent,ConnectionToPlayer, Context.BIND_AUTO_CREATE)
    }
    override fun onStop() {
        super.onStop()
        unbindService(ConnectionToPlayer)

    }

    object ConnectionToPlayer: ServiceConnection{

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("hi","bye")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            com.kakadurf.newProject.ServiceAPI.binder = Aidl.Stub.asInterface(service)

        }
    }
}