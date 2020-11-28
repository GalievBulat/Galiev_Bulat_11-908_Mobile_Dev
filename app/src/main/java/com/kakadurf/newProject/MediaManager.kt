package com.kakadurf.newProject

import android.content.Context
import android.media.MediaPlayer

public class MediaManager(private val context: Context){
    var binder: MusicBinder? = null
    private var mediaPlayer = MediaPlayer()

    fun passTrack(media:Media){
        if (mediaPlayer.isPlaying) {
            close()
        }
        com.kakadurf.newProject.helper.System.println("hrrr")
        binder?.listener?.listen()
        System.out.println(media.toString())
        mediaPlayer = MediaPlayer.create(context, media.getMedia())
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
        mediaPlayer.setOnCompletionListener {
            close()
            com.kakadurf.newProject.helper.System.println("hrrr")
            binder?.listener?.listen()
        }
    }
    private fun close(){
        mediaPlayer.stop()
        mediaPlayer.reset()
    }
    fun pauseTrack(){

        if (mediaPlayer.isPlaying){
            mediaPlayer.pause()
            com.kakadurf.newProject.helper.System.println("hehhe")
            binder?.listener?.listen()
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