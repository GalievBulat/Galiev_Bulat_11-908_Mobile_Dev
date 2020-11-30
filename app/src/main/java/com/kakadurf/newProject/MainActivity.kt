package com.kakadurf.newProject

import android.content.*
import android.net.ConnectivityManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import com.kakadurf.newProject.helper.System

class MainActivity : AppCompatActivity() {


    companion object{
        lateinit var musicAdapter:MusicAdapter
        val handler =  Handler{
            musicAdapter.endSong(it.what)
            true
        }
        var api:MusicPlayingService? = null
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api = ServiceAPI(applicationContext)

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

        val br: BroadcastReceiver = StateReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction("com.kakadurf.media_action")
        filter.addDataScheme("kakadurf")
        this.registerReceiver(br, filter)


       /* frl_frame.setOnClickListener {
            System.println("hii")
            val intent = Intent("com.kakadurf.media_action")
            intent.extras?.putString("action","pause")
            sendBroadcast(intent)

        }*/
//        RemoteViews("com.kakadurf.newProject",R.layout.piece_of_music).apply(applicationContext,frl_frame)

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