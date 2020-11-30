package com.kakadurf.newProject

import android.content.Context
import android.os.Build
import com.kakadurf.newProject.helper.System

class ServiceAPI(context: Context): MusicPlayingService, AutoCloseable {
    private val musicRepository = MusicRepository()
    private val notifService = NotificationThingEhh(context)

    companion object {
        var currentTrack: MusicPiece? = null
        var playing: Boolean = false
        var next = false
    }
    var mBinder: Aidl? = null

    private fun updNotif(playing: Boolean = true){
        notifService.doNotify(notifService.makeNotif(playing),
            currentTrack?.name ?: "name", currentTrack?.author?:"band")
    }
    private fun setCurrent(ct:MusicPiece) {
        currentTrack = ct
        mBinder?.onMusicComplete(object : MListener.Stub() {
            override fun listen() {
                System.println(currentTrack.toString() + " closed")
                currentTrack?.let {
                    MainActivity.handler.sendEmptyMessage(
                        -1
                    )
                }
            }
        })
        currentTrack?.let {
            Fr2.musicClosingHandler?.sendEmptyMessage(musicRepository.find(it))
        }
    }

    override fun playNewMusic(musicPiece: MusicPiece) {
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
        playing = true
        updNotif(playing)
    }
    override fun stopMusic() {
        mBinder?.stop()
        currentTrack = null
        playing = false
        updNotif(playing)
    }
    override fun next(int: Int) {
        setCurrent(musicRepository.findNext(int))
        mBinder?.pass(currentTrack)
        System.println("nexted")
        playing = true
        next = true
        updNotif(playing)
    }
    override fun nextFromCurrent() {
        currentTrack?.let {setCurrent(musicRepository.findNext(it))}
        mBinder?.pass(currentTrack)
        playing = true
        next = true
        updNotif(playing)
    }
    override fun prevFromCurrent() {
        currentTrack?.let {  setCurrent(musicRepository.findPrev(it))}
        mBinder?.pass(currentTrack)
        playing = true
        next = true
        updNotif(playing)
    }

    override fun prev(int: Int) {
        setCurrent(musicRepository.findPrev(int))
        mBinder?.pass(currentTrack)
        playing = true
        next = true
        updNotif(playing)
    }
    override fun pause(){
        mBinder?.pause()
        currentTrack?.let {
        MainActivity.handler.sendEmptyMessage(-1)}
        playing = false
        updNotif(playing)
    }
    override fun resume(){
        mBinder?.resume()
        currentTrack?.let {
            MainActivity.handler.sendEmptyMessage(-1)}
        playing = true
        updNotif(playing)
    }


    override fun setBinder(binder: Aidl) {
        mBinder =binder
    }

    override fun getBinder(): Aidl? {
        return mBinder
    }
    override fun close(){
        playing = false
        mBinder?.stop()
        MainActivity.handler.sendEmptyMessage(
            -1 )
        currentTrack = null
    }
}
