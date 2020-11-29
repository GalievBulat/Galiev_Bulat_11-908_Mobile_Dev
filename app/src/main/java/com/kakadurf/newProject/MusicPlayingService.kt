package com.kakadurf.newProject

interface MusicPlayingService{
    fun playMusic(musicPiece: MusicPiece)
    fun stopMusic()
    fun next(int: Int)
    fun prev(int: Int)
    fun pause()
    fun setBinder(binder: Aidl)
    fun getBinder(): Aidl?

}