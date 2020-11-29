package com.kakadurf.newProject

import com.kakadurf.newProject.helper.System

class ServiceAPI: MusicPlayingService {
        private val musicRepository = MusicRepository()
        private var currentTrack:MusicPiece? = null

         var mBinder: Aidl? = null

        override fun playMusic(musicPiece: MusicPiece) {
            val prev = currentTrack
            if (prev != musicPiece) {
                prev?.run {
                    mBinder?.stop()
                }
                mBinder?.pass(musicPiece)
                currentTrack = musicPiece
            }  else {
                mBinder?.resume()
            }
            mBinder?.onMusicComplete(object : MListener.Stub() {
                override fun listen() {
                    System.println(currentTrack.toString() + " closed")
                    currentTrack?.let {
                    MainActivity.handler.sendEmptyMessage(
                        musicRepository.find(it))
                }
                }
            })
        }
     override fun stopMusic() {
            mBinder?.stop()
        }
     override fun next(int: Int) {
            mBinder?.pass(musicRepository.findNext(int))

        }
     override fun prev(int: Int) {
            mBinder?.pass(musicRepository.findPrev(int))
        }
     override fun pause(){
            mBinder?.pause()
        }

     override fun setBinder(binder: Aidl) {
         mBinder =binder
     }

    override fun getBinder(): Aidl? {
        return mBinder
    }

    val isRunning
        get() = mBinder==null

}
