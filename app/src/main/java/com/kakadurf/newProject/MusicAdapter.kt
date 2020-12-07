package com.kakadurf.newProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakadurf.newProject.interfaces.MusicPlayingService
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_list_piece_of_music.*

class MusicAdapter(private val musicList:List<MusicPiece>, private val service: MusicPlayingService): RecyclerView.Adapter<MusicHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder =
        MusicHolder.create(parent,service)
    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: MusicHolder, position: Int) =
        holder.bind(musicList[position])
}
class MusicHolder(override val containerView: View, private val service: MusicPlayingService): RecyclerView.ViewHolder(containerView),LayoutContainer {

    private var music: MusicPiece? = null
    fun bind(music: MusicPiece) {
        this.music = music
        tv_name.text = music.name
        tv_album.text = music.album
        tv_author.text = music.author
        iv_cover.setImageResource(music.cover)
        containerView.setOnClickListener{
            service.playNewMusic(music)
        }
    }
    companion object{
        fun create(parent:ViewGroup,service: MusicPlayingService): MusicHolder{
            return MusicHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_list_piece_of_music,
                parent,false), service)
        }
    }
}
