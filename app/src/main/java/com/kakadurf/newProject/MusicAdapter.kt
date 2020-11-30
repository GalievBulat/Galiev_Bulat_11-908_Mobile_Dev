package com.kakadurf.newProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakadurf.newProject.helper.System
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.piece_of_music.*

class MusicAdapter(private val musicList:List<MusicPiece>,private val musicPlayingService: MusicPlayingService): RecyclerView.Adapter<MusicHolder>(){
    fun endSong(id:Int){
        if (id == -1)
            ServiceAPI.playing= false
        notifyDataSetChanged()
        //notifyItemChanged(id)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder =
        MusicHolder.create(parent,musicPlayingService)
    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: MusicHolder, position: Int) =
        holder.bind(musicList[position],position)
}
class MusicHolder(override val containerView: View,private val musicPlayingService: MusicPlayingService): RecyclerView.ViewHolder(containerView),LayoutContainer {
    private fun changeState(playing: Boolean){
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
    private fun close(){
        iv_cancel.visibility = View.GONE
        changeState(false)
        musicPlayingService.stopMusic()
    }
    fun bind(music: MusicPiece,id:Int) {
        if (ServiceAPI.next )
            ServiceAPI.playing = true
        ServiceAPI.next = false
        tv_name.text = music.name
        tv_album.text = music.album
        tv_author.text = music.author
        iv_cover.setImageResource(music.cover)
        var isCurrentAndPlaying = music == ServiceAPI.currentTrack && ServiceAPI.playing
        changeState(isCurrentAndPlaying)
        System.println("next " + ServiceAPI.playing)
        if (isCurrentAndPlaying){
            iv_cancel.visibility = View.VISIBLE
        } else {
            iv_cancel.visibility = View.GONE
        }
        with(musicPlayingService) {
            iv_play.setOnClickListener {
                isCurrentAndPlaying = music == ServiceAPI.currentTrack && ServiceAPI.playing
                if (!isCurrentAndPlaying) {
                    //ServiceAPI.next = true
                    playNewMusic(music)
                    changeState(true)
                    iv_cancel.visibility = View.VISIBLE
                } else {
                    pause()
                    changeState(false)
                }
            }
            iv_lyrics.setOnClickListener {
                LyricsDialog(music,containerView.context).createDialog()
            }
            iv_cancel.setOnClickListener {
                close()
            }
            iv_next.setOnClickListener {
                close()
                next(id)
                System.println("next " + ServiceAPI.playing)
            }
            iv_prev.setOnClickListener {
                close()
                prev(id)
            }
        }
    }
    companion object{
        fun create(parent:ViewGroup,musicPlayingService: MusicPlayingService): MusicHolder{
            return MusicHolder(LayoutInflater.from(parent.context).inflate(R.layout.piece_of_music,
                parent,false),musicPlayingService)
        }
    }
}
