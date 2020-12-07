package com.kakadurf.newProject;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

private const val ACTION = "com.kakadurf.media_action"
private const val NOTIFICATION_CHANNEL_ID = "kakadurf/notifChannel"
private const val NOTIFICATION_ID = 22

class NotificationService (val context: Context){
    private var intentSwitch = Intent(ACTION, Uri.parse("kakadurf://resume"))
    private val intentNext = Intent(ACTION, Uri.parse("kakadurf://next"))
    private val intentPrev = Intent(ACTION, Uri.parse("kakadurf://prev"))
    private val intentStop = Intent(ACTION, Uri.parse("kakadurf://stop"))
    private val service: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager

    private fun pendingIntentFromIntent(intent: Intent,id: Int,default:Boolean = true): PendingIntent =
        PendingIntent.getBroadcast(context,id,intent,if (default) 0 else PendingIntent.FLAG_UPDATE_CURRENT)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChanel(): NotificationChannel {
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID,"hi", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "ho"
        //service.createNotificationChannel(channel)
        return channel
    }
    fun createBaseNotification(): NotificationCompat.Builder{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            service.createNotificationChannel(createChanel())
        return context.let {
            NotificationCompat.Builder(it, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(PendingIntent.getActivity(context,25,
                    Intent(context,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0))
        }

    }
    fun makeBaseView(playing: Boolean = false): RemoteViews {
        intentSwitch = if (playing)
            Intent(ACTION, Uri.parse("kakadurf://pause"))
        else
            Intent(ACTION, Uri.parse("kakadurf://resume"))
        val views =RemoteViews(context.packageName,R.layout.notification_service_alert)
        views.setImageViewResource(R.id.iv_cover,R.drawable.ic_launcher_foreground)
        views.setImageViewResource(R.id.iv_prev,android.R.drawable.ic_media_rew)
        views.setImageViewResource(R.id.iv_next,android.R.drawable.ic_media_ff)
        views.setImageViewResource(R.id.iv_play,if (playing)  android.R.drawable.ic_media_pause
            else android.R.drawable.ic_media_play)
        views.setImageViewResource(R.id.iv_cancel,android.R.drawable.ic_menu_close_clear_cancel)
        views.setOnClickPendingIntent(R.id.iv_prev, pendingIntentFromIntent(intentPrev,21))
        views.setOnClickPendingIntent(R.id.iv_next, pendingIntentFromIntent(intentNext,22))
        views.setOnClickPendingIntent(R.id.iv_play, pendingIntentFromIntent(intentSwitch,23,false))
        views.setOnClickPendingIntent(R.id.iv_cancel, pendingIntentFromIntent(intentStop,24))
        return views
    }

    fun doNotify(notificationBuilder: NotificationCompat.Builder, views:  RemoteViews, name:String,
                 author: String,album: String, @DrawableRes cover: Int){
        views.setTextViewText(R.id.tv_name,name)
        views.setTextViewText(R.id.tv_author,author)
        views.setTextViewText(R.id.tv_album,album)
        views.setImageViewResource(R.id.iv_cover,cover)
        service.notify(NOTIFICATION_ID,notificationBuilder.setCustomContentView(views).build())
    }
    fun close(){
        service.cancel(NOTIFICATION_ID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            service.deleteNotificationChannel(NOTIFICATION_CHANNEL_ID)
    }
}