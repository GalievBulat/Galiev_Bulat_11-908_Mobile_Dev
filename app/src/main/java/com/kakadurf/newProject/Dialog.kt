package com.kakadurf.newProject

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class Dialog(private val music: MusicPiece): DialogFragment( ){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(music.name + " Lyrics")
            .setMessage(music.text)
            .setIcon(music.cover)
        return builder.create()
    }
}