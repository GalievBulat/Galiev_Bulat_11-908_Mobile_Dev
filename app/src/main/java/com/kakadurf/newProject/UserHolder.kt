package com.kakadurf.newProject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user.*

class UserHolder(override val containerView: View,private val itemClick: (id: Int) -> Unit) : RecyclerView.ViewHolder(containerView),LayoutContainer {
    private var user:User? = null
    init{
        containerView.setOnClickListener{
            user?.let {
                itemClick(user!!.id)
            }

        }
    }
    companion object{
        fun getInstance(parent: ViewGroup, itemClick: (id: Int) -> Unit):UserHolder =
            UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.user,parent,false),itemClick)
    }
    fun bind(user: User) {
        this.user = user

        with(user) {
            tv_desc.text = description
            tv_name.text = name
            iv_avatar.setImageResource(photoId)
        }
    }

}