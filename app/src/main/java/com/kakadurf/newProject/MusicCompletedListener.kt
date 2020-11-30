package com.kakadurf.newProject

class MusicCompletedListener{
    fun setOnMusicComplete(onCompleteListener: OnCompleteListener ){
       onCompleteListener.setOnComplete()
    }
}
interface OnCompleteListener{
    fun setOnComplete()
}