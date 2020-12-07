package com.kakadurf.newProject

import android.content.*
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kakadurf.newProject.helper.System
import com.kakadurf.newProject.interfaces.MusicPlayingService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var api: MusicPlayingService? = null
    private var broadcastReceiver: BroadcastReceiver? = null
    private var connection:ServiceConnection ? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api = MusicPlayingController(applicationContext)
        broadcastReceiver = StateReceiver(api as MusicPlayingController)
        connection =  object:  ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                System.out.println("bye")
            }
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                api?.setBinder(InterproccessMusicCommunication.Stub.asInterface(service))
                api?.getBinder()?.onMusicComplete(object : MListener.Stub() {
                    override fun onMusicComplete() {
                        System.println(api?.getCurrent().toString() + " closed")
                        api?.getCurrent()?.let {
                            api?.stopMusic()
                        }
                    }
                })
            }
        }
        render(FragmentControl(api as MusicPlayingController))
        botnav_bn.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.item_1-> {
                    render(FragmentList(api as MusicPlayingController))
                    true
                }
                R.id.item_2-> {
                    render(FragmentControl(api as MusicPlayingController))
                    true
                }
                else -> false
            }
        }
        botnav_bn.setOnNavigationItemReselectedListener {  }
        botnav_bn.selectedItemId = R.id.item_2
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        filter.addAction("com.kakadurf.media_action")
        filter.addDataScheme("kakadurf")
        this.registerReceiver(broadcastReceiver, filter)
        val intent = Intent(this,MusicHandlingService::class.java)
        startForegroundService(intent)
        bindService(intent, connection as ServiceConnection, Context.BIND_AUTO_CREATE)
    }
    override fun onDestroy() {
        System.out.println("destroyed main")
        connection?.let { unbindService(it) }
        this.unregisterReceiver(broadcastReceiver)
        api?.close()
        super.onDestroy()
    }
    private fun render ( fr: Fragment){
        val trans = supportFragmentManager.beginTransaction()
        with(trans) {
            replace(R.id.frl_frame, fr)
            commit()
        }
    }
}