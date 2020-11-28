package com.kakadurf.newProject

import android.view.View
import android.widget.Toast
import com.kakadurf.newProject.helper.System
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*

class ScreenManipulator() {
    companion object {
        fun manipulate(int: Int) {
            System.println("oh2")
            MainActivity.musicAdapter.endSong(int)
        }
    }
}