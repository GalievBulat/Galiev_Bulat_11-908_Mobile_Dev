package com.kakadurf.newProject

import com.kakadurf.newProject.helper.System

class MusicBinder( val mediaManager: MediaManager) : Aidl.Stub() {
     var listener:MListener? = null

   private var currentTrack:MusicPiece? = null
    override fun pause() {
        mediaManager.pauseTrack()
    }

    override fun play() {
        //mediaManager.resumeTrack()
    }

    override fun next() {
        currentTrack = currentTrack?.let { MusicRepository().findNext(it)}
        currentTrack?.let { mediaManager.passTrack(it) }
    }

    override fun pass(composition: MusicPiece?) {
        if(currentTrack!=composition) {
            composition?.let { mediaManager.passTrack(it) }
            currentTrack = composition
        } else{
            mediaManager.resumeTrack()
        }
    }

    override fun prev() {
        currentTrack = currentTrack?.let { MusicRepository().findPrev(it)}
        currentTrack?.let { mediaManager.passTrack(it) }
    }

    override fun onMusicComplete(listener: MListener?) {
        this.listener = listener
    }

    override fun stop() {
        mediaManager.stopTrack()
    }
    override fun getPid(): Int{
        return android.os.Process.myPid()
    }
}