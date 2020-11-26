package com.kakadurf.newProject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

public class MusicHandlingService: Service(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val view = RemoteViews(packageName,R.layout.alert)
        val notification = NotificationCompat.Builder(applicationContext,"rr")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCustomContentView(RemoteViews( packageName,R.layout.alert))
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            //.setCustomContentView(view)
        val service: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("rr","hi", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "ho"
        service.createNotificationChannel(channel)
        //service.notify()
        startForeground(1,notification.build())
    }

    override fun onBind(intent: Intent?): IBinder?  {
        Log.d("hi","hi")
        return MusicBinder(MediaManager(applicationContext))
    }

}