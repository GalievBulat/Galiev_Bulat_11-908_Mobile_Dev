package com.kakadurf.newProject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kakadurf.newProject.helper.System
import com.kakadurf.newProject.interfaces.MusicPlayingService

class StateReceiver(musicPlayingService :MusicPlayingService) : BroadcastReceiver() {
    private val serviceAPI= musicPlayingService
    override fun onReceive(context: Context, intent: Intent) {
        System.println("receive")
        System.println(intent.data.toString())
        when(intent.data.toString()){
            "kakadurf://resume"->serviceAPI.resume()
            "kakadurf://pause"->serviceAPI.pause()
            "kakadurf://next"->
                serviceAPI.next()
            "kakadurf://prev"->
                serviceAPI.prev()
            "kakadurf://stop"->
                serviceAPI.stopMusic()
        }
    }
}
