package com.kakadurf.newProject

class MusicBinder( val mediaManager: MediaManager) : Aidl.Stub() {
     var listener:MListener? = null

    override fun pause() {
        mediaManager.pauseTrack()
    }

/*
    override fun next(int: Int) {
        currentTrack =  MusicRepository().findNext(int)
        currentTrack?.let { mediaManager.passTrack(it) }
        *//*currentTrack = currentTrack?.let { MusicRepository().findNext(it)}
        currentTrack?.let { mediaManager.passTrack(it) }*//*
    }*/

    override fun pass(composition: MusicPiece?): Boolean {
        composition?.let { mediaManager.passTrack(it) }
        return true
    }

    override fun resume() {
        mediaManager.resumeTrack()
    }
/*
    override fun prev(int: Int) {
        currentTrack =  MusicRepository().findNext(int)
        currentTrack?.let { mediaManager.passTrack(it) }
        *//*currentTrack = currentTrack?.let { MusicRepository().findPrev(it)}
        currentTrack?.let { mediaManager.passTrack(it) }*//*
    }*/

    override fun onMusicComplete(listener: MListener?): Boolean {
        this.listener = listener
        return true
    }

    override fun stop():Boolean {
        mediaManager.stopTrack()
        /*
        listener?.listen()*/
        return true
    }
    override fun getPid(): Int{
        return android.os.Process.myPid()
    }
}