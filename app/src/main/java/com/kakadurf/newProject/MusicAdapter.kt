package com.kakadurf.newProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.piece_of_music.*

class MusicAdapter(private val musicList:List<MusicPiece>,private val musicPlayingService: MusicPlayingService): RecyclerView.Adapter<MusicHolder>(){
    fun endSong(id:Int){
        musicList[id].playing= false
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
        tv_name.text = music.name
        tv_album.text = music.album
        tv_author.text = music.author
        iv_cover.setImageResource(music.cover)
        changeState(music.playing)
        if (music.playing){
            iv_cancel.visibility = View.VISIBLE

        } else {
            iv_cancel.visibility = View.GONE
            changeState(false)
        }
        with(musicPlayingService) {
            iv_play.setOnClickListener {
                music.playing = !music.playing
                changeState(music.playing)
                if (music.playing) {
                    playMusic(music)
                    iv_cancel.visibility = View.VISIBLE
                } else{
                    pause()
                }
            }
            iv_lyrics.setOnClickListener {
//            val myDialogFragment = Dialog(music)
//            myDialogFragment.show(manager, "myDialog")
            }
            iv_cancel.setOnClickListener {
                close()
            }
            iv_next.setOnClickListener {
                close()
                next(id)
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
