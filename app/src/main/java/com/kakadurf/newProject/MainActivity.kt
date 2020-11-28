package com.kakadurf.newProject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.kakadurf.newProject.helper.System

class MainActivity : AppCompatActivity() {

    object Listener: MListener.Stub() {

        override fun listen() {
            System.println("ll")
            musicAdapter.endSong(0)
        }

    }
    companion object{

        lateinit var musicAdapter:MusicAdapter
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)




        /*val view = RemoteViews(packageName,R.layout.alert)
        view.setOnClickPendingIntent(R.id.aaaaa, PendingIntent.getActivity(applicationContext,1,Intent(this,MainActivity::class.java),PendingIntent.FLAG_ONE_SHOT))
        val notification = NotificationCompat.Builder(applicationContext,"5")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCustomContentView(view)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
        //.setCustomContentView(view)
        val service: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("5","hi", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "ho"
        service.createNotificationChannel(channel)
        service.notify(1,notification.build())*/



        val repository =  MusicRepository()
        musicAdapter = MusicAdapter(repository.getAll())
        rv_main.adapter = musicAdapter
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
            System.out.println("hi")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            ServiceAPI.binder = Aidl.Stub.asInterface(service)
            ServiceAPI.binder?.onMusicComplete(Listener )
        }
    }


}