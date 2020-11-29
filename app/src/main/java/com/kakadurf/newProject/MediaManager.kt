package com.kakadurf.newProject

import android.content.Context
import android.media.MediaPlayer
import com.kakadurf.newProject.helper.System

class MediaManager(private val context: Context){
    var binder: MusicBinder? = null
    private var mediaPlayer = MediaPlayer()

    fun passTrack(media:Media){
        if (mediaPlayer.isPlaying) {
            close()
        }
        System.out.println(media.toString())
        mediaPlayer = MediaPlayer.create(context, media.getMedia())
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
        mediaPlayer.setOnCompletionListener {
            close()
        }
    }
    private fun close(){
        mediaPlayer.stop()
        mediaPlayer.reset()
        System.println("hrrr")
        binder?.listener?.listen()
    }
    fun pauseTrack(){

        if (mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
    }
    fun stopTrack(){
        if (mediaPlayer.isPlaying){
            close()
        }
    }
    fun resumeTrack(){
        if (!mediaPlayer.isPlaying){
            mediaPlayer.start()
        }
    }
    val isRunning
        get() = mediaPlayer.currentPosition>0

}