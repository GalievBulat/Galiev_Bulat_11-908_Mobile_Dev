package com.kakadurf.newProject

import android.content.Context
import com.kakadurf.newProject.FragmentControl.Companion.viewUpdatingHandler
import com.kakadurf.newProject.helper.System
import com.kakadurf.newProject.interfaces.MusicPlayingService

class MusicPlayingController(context: Context): MusicPlayingService, AutoCloseable {
    private var currentTrack: MusicPiece? = null
    private var playing: Boolean = false
    var mBinder: InterproccessMusicCommunication? = null
    private val musicRepository = MusicRepository()
    private val notifService = NotificationService(context)
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
        viewUpdatingHandler?.sendEmptyMessage(-1)
    }
    override fun stopMusic() {
        close()
    }
    override fun next() {
        currentTrack?.let {setCurrent(musicRepository.findNext(it))}
        mBinder?.pass(currentTrack)
        playing = true
        updNotif(playing)
        viewUpdatingHandler?.sendEmptyMessage(-1)
    }
    override fun prev() {
        currentTrack?.let {  setCurrent(musicRepository.findPrev(it))}
        mBinder?.pass(currentTrack)
        playing = true
        updNotif(playing)
        viewUpdatingHandler?.sendEmptyMessage(-1)
    }
    override fun pause(){
        mBinder?.pause()
        viewUpdatingHandler?.sendEmptyMessage(-1)
        playing = false
        updNotif(playing)
    }
    override fun resume(){
        mBinder?.resume()
        viewUpdatingHandler?.sendEmptyMessage(-1)
        playing = true
        updNotif(playing)
    }


    override fun setBinder(binder: InterproccessMusicCommunication) {
        mBinder =binder
    }

    override fun getBinder(): InterproccessMusicCommunication? {
        return mBinder
    }
    override fun close(){
        playing = false
        currentTrack = null
        mBinder?.stop()
        viewUpdatingHandler?.sendEmptyMessage(-1 )
        notifService.close()
        System.println("closed")
    }
    override fun getCurrent(): MusicPiece? = currentTrack
    override fun isPlaying(): Boolean {
        return playing
    }

    private fun updNotif(playing: Boolean = true){
        with(notifService) {
            doNotify(
                createBaseNotification(), makeBaseView(playing),
                currentTrack?. name ?: "name",
                currentTrack?.author ?: "band",
                currentTrack?.album ?: "band",
                currentTrack?.cover ?: R.drawable.ic_launcher_foreground
            )
        }
    }
    private fun setCurrent(ct:MusicPiece,updateView:Boolean = true) {
        currentTrack = ct
        if (updateView)
            currentTrack?.let {
                viewUpdatingHandler?.sendEmptyMessage(-1)
            }
    }

}
