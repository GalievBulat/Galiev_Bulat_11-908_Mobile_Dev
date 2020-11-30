package com.kakadurf.newProject

import android.app.*
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.kakadurf.newProject.helper.System
import java.util.zip.Inflater


 class MusicHandlingService: Service(){
     companion object{

         /*
         var handler: Handler? = null*/
     }
    lateinit var  binder:MusicBinder
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()


        //service.notify()
        startForeground(22,NotificationThingEhh(applicationContext).doWork().build())
        /*handler = Handler{
            startForeground(22,notification?.build())
            return@Handler true
        }*/

    }

    override fun onBind(intent: Intent?): IBinder?  {
        Log.d("hi","hi")
        binder = MusicBinder(MediaManager(applicationContext))
        binder.mediaManager.binder = binder
        return binder
    }

}