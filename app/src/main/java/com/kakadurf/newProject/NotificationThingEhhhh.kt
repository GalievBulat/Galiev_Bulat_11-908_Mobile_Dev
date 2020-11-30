package com.kakadurf.newProject;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

private const val ACTION = "com.kakadurf.media_action"

class NotificationThingEhh (val context: Context){

    private val service: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager
    @RequiresApi(Build.VERSION_CODES.O)
    fun createChanel(): NotificationChannel {
        val channel = NotificationChannel("5","hi", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "ho"
        //service.createNotificationChannel(channel)
        return channel
    }
    fun makeNotif(playing: Boolean = false): NotificationCompat.Builder {
        val intentPause = if (playing)  Intent(ACTION, Uri.parse("kakadurf://pause"))
            else  Intent(ACTION, Uri.parse("kakadurf://resume"))
        val intentNext = Intent(ACTION, Uri.parse("kakadurf://next"))
        val intentPrev = Intent(ACTION, Uri.parse("kakadurf://prev"))
        val intentStop = Intent(ACTION, Uri.parse("kakadurf://stop"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            service.createNotificationChannel(createChanel())
        val notification = context.let {
            NotificationCompat.Builder(it, "5")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("music")
                .setContentText("band")
                .addAction(android.R.drawable.ic_media_previous, "previous", PendingIntent.getBroadcast
                    (context, 21, intentPrev, 0))
                .addAction(android.R.drawable.ic_media_play,  "pause".takeIf {playing}?: "play", PendingIntent.getBroadcast
                    (context, 22, intentPause, 0))
                .addAction(android.R.drawable.ic_media_next, "next", PendingIntent.getBroadcast
                    (context, 23, intentNext,0))
                .addAction(android.R.drawable.ic_menu_close_clear_cancel, "cancel", PendingIntent.getBroadcast
                    (context, 24, intentStop, 0))
                .setContentIntent(PendingIntent.getActivity(context,25, Intent(context,MainActivity::class.java),0))
        }
        return notification
    }

    fun doNotify(notificationBuilder: NotificationCompat.Builder,text1:String, text2: String){
        service.notify(22,notificationBuilder
            .setContentTitle(text1)?.setContentText(text2)?.build())
    }
}