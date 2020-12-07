package com.kakadurf.newProject

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.kakadurf.newProject.interfaces.Media

data class MusicPiece(val name:String,
@DrawableRes val cover: Int,
@RawRes val music:Int, val text:String, val author:String, val album:String): Parcelable,
    Media {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(cover)
        parcel.writeInt(music)
        parcel.writeString(text)
        parcel.writeString(author)
        parcel.writeString(album)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MusicPiece> {
        override fun createFromParcel(parcel: Parcel): MusicPiece {
            return MusicPiece(parcel)
        }

        override fun newArray(size: Int): Array<MusicPiece?> {
            return arrayOf<MusicPiece?>(null)
            
        }
    }

    override fun getMedia(): Int {
        return music
    }
}