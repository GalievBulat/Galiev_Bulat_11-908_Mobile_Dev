package com.kakadurf.newProject

import android.app.AlertDialog
import android.content.Context

class LyricsDialog(private val music: MusicPiece, private val context: Context){
     fun createDialog()  {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(music.name + " Lyrics")
            .setMessage(music.text)
            .setIcon(music.cover)
            .setPositiveButton("Ok") { _, _ ->
            }
            .show()
    }
}