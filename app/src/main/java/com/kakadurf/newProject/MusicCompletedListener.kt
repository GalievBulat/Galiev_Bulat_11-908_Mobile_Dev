package com.kakadurf.newProject
import com.kakadurf.newProject.helper.System

class MusicCompletedListener{
    fun setOnMusicComplete(onCompleteListener: OnCompleteListener ){
       onCompleteListener.setOnComplete()
    }
}
interface OnCompleteListener{
    fun setOnComplete()
}