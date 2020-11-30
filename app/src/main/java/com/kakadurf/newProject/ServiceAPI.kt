package com.kakadurf.newProject

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.kakadurf.newProject.helper.System

@RequiresApi(Build.VERSION_CODES.O)
class ServiceAPI(val context: Context): MusicPlayingService, AutoCloseable {
        private val musicRepository = MusicRepository()
        private val notifService = com.kakadurf.newProject.NotificationThingEhh(context)
        private var currentTrack:MusicPiece? = null

         var mBinder: Aidl? = null

    private fun setCurrent(ct:MusicPiece){

        currentTrack = ct
        mBinder?.onMusicComplete(object : MListener.Stub() {
            override fun listen() {
                System.println(currentTrack.toString() + " closed")
                currentTrack?.let {
                    MainActivity.handler.sendEmptyMessage(
                        musicRepository.find(it))
                }
            }
        })
        currentTrack?.let {  Fr2.handler?.sendEmptyMessage(musicRepository.find(it))}
        /*
        MusicHandlingService.notification = MusicHandlingService.notification?.apply {
            setContentTitle(currentTrack?.name).setContentText(currentTrack?.author)
        }
        MusicHandlingService.handler?.sendEmptyMessage(0)*/
        System.println("upd")

        notifService.doNotify(currentTrack?.name ?: "name",currentTrack?.author ?: "author")
    }

        override fun playMusic(musicPiece: MusicPiece) {
            val prev = currentTrack
            if (prev != musicPiece) {
                prev?.run {
                    mBinder?.stop()
                }
                mBinder?.pass(musicPiece)
                setCurrent(musicPiece)
            }  else {
                mBinder?.resume()
            }
        }
    override fun stopMusic() {
           mBinder?.stop()
       }
    override fun next(int: Int) {
            setCurrent(musicRepository.findNext(int))
            mBinder?.pass(currentTrack)
    }
    override fun nextFromCurrent() {
        currentTrack?.let {  setCurrent(musicRepository.findNext(it))}
        mBinder?.pass(currentTrack)
    }
    override fun prevFromCurrent() {
        currentTrack?.let {  setCurrent(musicRepository.findPrev(it))}
        mBinder?.pass(currentTrack)
    }

    override fun prev(int: Int) {
        setCurrent(musicRepository.findPrev(int))
        mBinder?.pass(currentTrack)
    }
    override fun pause(){
           mBinder?.pause()
        currentTrack?.let {
        MainActivity.handler.sendEmptyMessage(
            musicRepository.find(it) )}
        }

    override fun setBinder(binder: Aidl) {
        mBinder =binder
    }

    override fun getBinder(): Aidl? {
        return mBinder
    }
    override fun close(){
        currentTrack?.playing = false
    }
    val isRunning
        get() = mBinder==null

}
