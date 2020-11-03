package com.kakadurf.newProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.frame31.*
import kotlinx.android.synthetic.main.frames.*

public class CardAdapter(val list: List<RecyclerView.Adapter<*>>):
    RecyclerView.Adapter<CardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        return CardHolder.getInstance(parent);
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: CardHolder, position: Int) =
        holder.bind(list[position])


}
class CardHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    private var photo: RecyclerView.Adapter<*>? = null
    companion object{
        fun getInstance(parent: ViewGroup): CardHolder =
            CardHolder(LayoutInflater.from(parent.context).inflate(R.layout.frames,parent,false))
    }
    fun bind(photo: RecyclerView.Adapter<*>) {
        this.photo = photo
        vp_rv.adapter = photo as RecyclerView.Adapter<*>
    }
}