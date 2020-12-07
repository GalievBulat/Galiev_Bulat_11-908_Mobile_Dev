package com.kakadurf.newProject

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.kakadurf.newProject.interfaces.MusicPlayingService
import kotlinx.android.synthetic.main.frame_control.*

class FragmentControl(private val musicPlayingService: MusicPlayingService): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.frame_control,container,false)
    }
    companion object{
        var viewUpdatingHandler:Handler? = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewUpdatingHandler =Handler{
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED))
                setSong(musicPlayingService.getCurrent())
            true
        }
    }

    override fun onResume() {
        super.onResume()
        setSong(musicPlayingService.getCurrent())
    }
    private fun setSong(music: MusicPiece?) {
        tv_name.text = music?.name ?: "name"
        tv_album.text = music?.album?: "album"
        tv_author.text = music?.author?: "author"
        music?.cover?.let { iv_cover.setImageResource(it) }
            ?: iv_cover.setImageResource(R.drawable.ic_launcher_foreground)
        var isPlaying = musicPlayingService.isPlaying()
        setPlayingState(isPlaying)
        if (isPlaying)
            iv_cancel.visibility = View.VISIBLE
        music?.let {
            with(musicPlayingService) {
                iv_play.setOnClickListener {
                    isPlaying = musicPlayingService.isPlaying()
                    if (!isPlaying) {
                        playNewMusic(music)
                        setPlayingState(true)
                        iv_cancel.visibility = View.VISIBLE
                    } else {
                        pause()
                        setPlayingState(false)
                    }
                }
                iv_cancel.setOnClickListener {
                    this@FragmentControl.close()
                }
                iv_next.setOnClickListener {
                    next()
                    setPlayingState(musicPlayingService.isPlaying())
                }
                iv_prev.setOnClickListener {
                    prev()
                    setPlayingState(musicPlayingService.isPlaying())
                }
                iv_lyrics.setOnClickListener {
                    context?.let { it1 -> LyricsDialog(music, it1).createDialog() }
                }
            }
        }
    }
    private fun close(){
        iv_cancel.visibility = View.GONE
        musicPlayingService.stopMusic()
    }
    private fun setPlayingState(playing: Boolean){
        with(iv_play) {
            if (playing && tag != android.R.drawable.ic_media_pause) {
                setImageResource(android.R.drawable.ic_media_pause)
                tag = android.R.drawable.ic_media_pause
            } else if (!playing && tag != android.R.drawable.ic_media_play) {
                setImageResource(android.R.drawable.ic_media_play)
                tag = android.R.drawable.ic_media_play
            }
        }
    }

}