package com.kakadurf.newProject

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        cMusicPiece?.let { its->
            tv_name.text = its.name
            tv_album.text = its.album
            tv_author.text = its.author
            iv_cover.setImageResource(its.cover)
            iv_lyrics.setOnClickListener {
                let { it1 -> LyricsDialog(its, view.context).createDialog() }
            }
        }
        musicClosingHandler =Handler{
            cMusicPiece = musicSet.get(it.what)
            true
        }
    }
    companion object{
        private var cMusicPiece: MusicPiece? = null
        var musicClosingHandler:Handler? = null
    }
}