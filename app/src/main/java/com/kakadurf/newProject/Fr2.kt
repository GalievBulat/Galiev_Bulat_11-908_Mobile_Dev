package com.kakadurf.newProject

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.frame2.*

class Fr2: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.frame2,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val musicSet= MusicRepository()

        cMusicPiece?.let {
            tv_name.text = cMusicPiece?.name
            tv_album.text = cMusicPiece?.album
            tv_author.text = cMusicPiece?.author
            cMusicPiece?.cover?.let { iv_cover.setImageResource(it) }
        }
        handler =     Handler{
            cMusicPiece = musicSet.get(it.what)

            true
        }
    }
    companion object{
        private var cMusicPiece: MusicPiece? = null
        var handler:Handler? = null

    }
}