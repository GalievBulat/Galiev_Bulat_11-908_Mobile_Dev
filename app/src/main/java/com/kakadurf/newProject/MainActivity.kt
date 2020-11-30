package com.kakadurf.newProject

import android.content.*
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kakadurf.newProject.helper.System
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object{
        lateinit var musicAdapter:MusicAdapter
        val handler =  Handler{
            musicAdapter.endSong(it.what)
            System.println("flushed!")
            true
        }
        var api:MusicPlayingService? = null
    }


    private lateinit var  br: BroadcastReceiver

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api = ServiceAPI(applicationContext)
        render(Fr2())

        botnav_bn.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.item_1-> {
                    render(Fr1())
                    true
                }
                R.id.item_2-> {
                    render(Fr2())
                    true
                }

                else -> false
            }
        }
        botnav_bn.setOnNavigationItemReselectedListener {  }
        supportFragmentManager

         br =  StateReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction("com.kakadurf.media_action")
        filter.addDataScheme("kakadurf")
        this.registerReceiver(br, filter)


        //setContentView(R.layout.the_main_thing)


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

        /*val repository =  MusicRepository()
        musicAdapter = MusicAdapter(repository.getAll(),api)
        rv_main.adapter = musicAdapter
        rv_main.layoutManager = LinearLayoutManager(this)*/
        val intent = Intent(this,MusicHandlingService::class.java)
        startForegroundService(intent)
        bindService(intent,ConnectionToPlayer, Context.BIND_AUTO_CREATE)

    }

    lateinit var iView: View
    private fun render ( fr: Fragment){
        val trans = supportFragmentManager.beginTransaction()
        with(trans) {
            replace(R.id.frl_frame, fr)
            commit()
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(ConnectionToPlayer)
        api?.close()
        this.unregisterReceiver(br)
    }


     object ConnectionToPlayer: ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

            System.out.println("bye")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            api?.setBinder(Aidl.Stub.asInterface(service))

        }
    }


}