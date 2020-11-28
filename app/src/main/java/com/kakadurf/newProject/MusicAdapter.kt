package com.kakadurf.newProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.piece_of_music.*

class MusicAdapter(private val musicList:List<MusicPiece>): RecyclerView.Adapter<MusicHolder>(){
    fun endSong(id:Int){
        musicList[id].paused = true
        notifyDataSetChanged()
        //notifyItemChanged(id)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder =MusicHolder.create(parent)


    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: MusicHolder, position: Int) =
        holder.bind(musicList[position])
}
class MusicHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),LayoutContainer {

    fun bind(music: MusicPiece) {
        tv_name.text = music.name
        tv_album.text = music.album
        aaaaa.text = music.author
        iv_cover.setImageResource(music.cover)
        if (music.paused){
            iv_play.setImageResource(R.drawable.ic_launcher_background)
        }
        with(ServiceAPI) {
            iv_play.setOnClickListener {
                playMusic(music)
            }
            iv_lyrics.setOnClickListener {
//            val myDialogFragment = Dialog(music)
//            myDialogFragment.show(manager, "myDialog")
            }
            iv_cancel.setOnClickListener {
                stopMusic()
            }
            iv_next.setOnClickListener {
                next()
            }
            iv_prev.setOnClickListener {
                prev()
            }
        }
    }
    companion object{
        fun create(parent:ViewGroup): MusicHolder{
            return MusicHolder(LayoutInflater.from(parent.context).inflate(R.layout.piece_of_music,parent,false))
        }
    }
}
