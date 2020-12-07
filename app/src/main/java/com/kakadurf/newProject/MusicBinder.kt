package com.kakadurf.newProject

class MusicBinder( val mediaManager: MediaManager) : InterproccessMusicCommunication.Stub() {
     var listener:MListener? = null

    override fun pause() {
        mediaManager.pauseTrack()
    }
    override fun pass(composition: MusicPiece?): Boolean {
        composition?.let { mediaManager.passTrack(it) }
        return true
    }

    override fun resume() {
        mediaManager.resumeTrack()
    }
    override fun onMusicComplete(listener: MListener?): Boolean {
        this.listener = listener
        return true
    }

    override fun stop():Boolean {
        mediaManager.stopTrack()
        return true
    }
    override fun getPid(): Int{
        return android.os.Process.myPid()
    }
    fun destroy(){
        mediaManager.destroy()
    }
}