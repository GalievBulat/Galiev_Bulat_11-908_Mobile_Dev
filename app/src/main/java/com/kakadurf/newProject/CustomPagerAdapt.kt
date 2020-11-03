package com.kakadurf.newProject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.frame31.*

public class CustomPagerAdapt(val list: List<Int>):
    RecyclerView.Adapter<PictHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictHolder {
        return PictHolder.getInstance(parent);
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: PictHolder, position: Int) =
        holder.bind(list[position])


}
class PictHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    private var photo: Int? = null
    companion object{
        fun getInstance(parent: ViewGroup): PictHolder =
            PictHolder(LayoutInflater.from(parent.context).inflate(R.layout.frame31,parent,false))
    }
    fun bind(photo: Int) {
        this.photo = photo
        iv_image.setImageResource(photo)
    }
}