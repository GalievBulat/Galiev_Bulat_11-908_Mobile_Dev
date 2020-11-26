package com.kakadurf.newProject

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import javax.security.auth.callback.Callback

public class MediaManager(private val context: Context,private val callback: com.kakadurf.newProject.Callback){

    private var mediaPlayer = MediaPlayer()

    fun passTrack(media:Media){
        if (mediaPlayer.isPlaying) {
            close()
        }
        Log.d("hi", media.toString())
        mediaPlayer = MediaPlayer.create(context, media.getMedia())
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
        mediaPlayer.setOnCompletionListener {
            close()
            callback.call()
        }
    }
    private fun close(){
        mediaPlayer.stop()
        mediaPlayer.release()
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