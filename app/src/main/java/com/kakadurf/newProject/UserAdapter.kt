package com.kakadurf.newProject


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter( private var list: List<User>, private val itemClick: (id:Int) -> Unit):RecyclerView.Adapter<UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder =
        UserHolder.getInstance(parent,itemClick)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) = holder.bind(list[position])

}