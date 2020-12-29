package com.kakadurf.newProject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakadurf.newProject.R
import com.kakadurf.newProject.entities.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.task_holder.*

class TaskHolder(override val containerView: View, private val onItemDelete: (id:Int) -> Unit,
                 private val onItemClick: (id:Int) -> Unit) : RecyclerView.ViewHolder(containerView),LayoutContainer {
    private var task: Task? = null
    companion object{
        fun getInstance(parent: ViewGroup, onItemDelete: (id:Int) -> Unit,
                        onItemClick: (id:Int) -> Unit): TaskHolder = TaskHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.task_holder,
            parent,
            false),
            onItemDelete, onItemClick)
    }
    fun bind(task: Task) {
        containerView.setOnClickListener{
            onItemClick(task.id)
        }
        this.task = task
        iv_cancel.setOnClickListener{
            onItemDelete(task.id)
        }
        with(task) {
            tv_desc.text = description
            tv_name.text = title
        }
    }
}
class TaskAdapter(private var list: List<Task>, private val onItemDelete: (id:Int) -> Unit,
                  private val onItemClick: (id:Int) -> Unit):RecyclerView.Adapter<TaskHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder =
        TaskHolder.getInstance(
            parent,
            onItemDelete, onItemClick
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) = holder.bind(list[position])
}