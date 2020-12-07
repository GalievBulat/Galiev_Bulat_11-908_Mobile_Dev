package com.kakadurf.newProject.interfaces

import com.kakadurf.newProject.InterproccessMusicCommunication
import com.kakadurf.newProject.MusicPiece

interface MusicPlayingService{
    fun playNewMusic(musicPiece: MusicPiece)
    fun stopMusic()
    fun next()
    fun prev()
    fun pause()
    fun resume()
    fun setBinder(binder: InterproccessMusicCommunication)
    fun getBinder(): InterproccessMusicCommunication?
    fun close()
    fun getCurrent(): MusicPiece?
    fun isPlaying(): Boolean
}