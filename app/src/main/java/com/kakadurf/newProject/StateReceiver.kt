package com.kakadurf.newProject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kakadurf.newProject.helper.System

class StateReceiver : BroadcastReceiver() {

    private val serviceAPI= MainActivity.api
    override fun onReceive(context: Context, intent: Intent) {
        System.println("receive")
        System.println(intent.data.toString())
        when(intent.data.toString()){
            "kakadurf://pause"-> serviceAPI?.pause()
            "kakadurf://next"->serviceAPI?.nextFromCurrent()
            "kakadurf://prev"->serviceAPI?.prevFromCurrent()
            "kakadurf://stop"->serviceAPI?.stopMusic()
        }
    }
}
