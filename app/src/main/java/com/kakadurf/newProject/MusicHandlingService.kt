package com.kakadurf.newProject

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi


class MusicHandlingService: Service(){

    private lateinit var  binder:MusicBinder
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        startForeground(22,NotificationThingEhh(applicationContext).makeNotif().build())
    }

    override fun onBind(intent: Intent?): IBinder?  {
        Log.d("hi","hi")
        binder = MusicBinder(MediaManager(applicationContext))
        binder.mediaManager.binder = binder
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}