package com.kakadurf.newProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post.*

public class CardAdapter(val list: List<Post>):
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
    private var post: Post? = null
    companion object{
        fun getInstance(parent: ViewGroup): CardHolder =
            CardHolder(LayoutInflater.from(parent.context).inflate(R.layout.post,parent,false))
    }
    fun bind(post: Post) {
        this.post = post
        vp_rv.adapter = post.photos
        tv_main.text = post.text
    }
}