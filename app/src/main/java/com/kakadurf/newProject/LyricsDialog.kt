package com.kakadurf.newProject

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class LyricsDialog(private val music: MusicPiece, private val context: Context){
     fun createDialog()  {
       /* AlertDialog.Builder(context)
            .setTitle("Adding")
            .setMessage("What to add?")
            .setPositiveButton("Ok") { _, _ ->
                addV(main.et_highlight,main.et_desc,main.et_pos)
                userAdapter?.notifyDataSetChanged()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setView(main)
            .show()*/
        val builder = AlertDialog.Builder(context)
        builder.setTitle(music.name + " Lyrics")
            .setMessage(music.text)
            .setIcon(music.cover)
            .setPositiveButton("Ok") { _, _ ->
            }
            .show()
    }
}