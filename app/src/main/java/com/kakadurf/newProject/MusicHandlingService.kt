package com.kakadurf.newProject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.kakadurf.newProject.helper.System
import java.util.zip.Inflater

 class MusicHandlingService: Service(){
    lateinit var  binder:MusicBinder
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val view = RemoteViews(packageName,R.layout.hh)
        view.setOnClickPendingIntent(R.id.brr, PendingIntent.getActivity(applicationContext,1,Intent(this,MainActivity::class.java),
            PendingIntent.FLAG_ONE_SHOT))
        val notification = NotificationCompat.Builder(applicationContext,"5")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContent(view)
            //.setStyle(NotificationCompat.DecoratedCustomViewStyle())
            //.setCustomContentView(view)
        view.setViewVisibility(R.id.brr, View.VISIBLE)
        val service: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("5","hi", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "ho"
        service.createNotificationChannel(channel)
        //service.notify()
        startForeground(22,notification.build())

    }

    override fun onBind(intent: Intent?): IBinder?  {
        Log.d("hi","hi")
        binder = MusicBinder(MediaManager(applicationContext))
        binder.mediaManager.binder = binder
        return binder
    }

}