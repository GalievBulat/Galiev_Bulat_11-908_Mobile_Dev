package com.kakadurf.newProject

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class MusicPiece(val name:String,
@DrawableRes val cover: Int,
@RawRes val music:Int, val text:String, val author:String, val album:String)