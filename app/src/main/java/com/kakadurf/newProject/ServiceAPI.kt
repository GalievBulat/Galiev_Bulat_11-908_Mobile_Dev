package com.kakadurf.newProject
 class ServiceAPI {
    companion object {
        var binder: Aidl? = null
        fun playMusic(musicPiece: MusicPiece) {
            binder?.pass(musicPiece)
        }
        fun stopMusic() {
            binder?.stop()
        }
        fun next() {
            binder?.next()
        }
        fun prev() {
            binder?.prev()
        }
        val isRunning
        get() = binder==null

    }
}
