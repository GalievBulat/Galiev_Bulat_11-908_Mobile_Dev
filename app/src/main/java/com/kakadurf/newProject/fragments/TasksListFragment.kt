package com.kakadurf.newProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kakadurf.newProject.R
import com.kakadurf.newProject.entities.Task
import com.kakadurf.newProject.view.TaskAdapter
import kotlinx.android.synthetic.main.tasks_list_fragment.*

class TasksListFragment(private var list: List<Task>?,
                        private val onEditTask :(Int)->Unit,
                        private val onAddTask: ()->Unit,
                        private val removeTask: (Int)->Unit): Fragment() {
    var taskAdapter: TaskAdapter?= null
    //lateinit var main: View
    lateinit var inflater:LayoutInflater
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //main = inflater.inflate(R.layout.task_manipulating_fragment,null)
        this.inflater = inflater
        return inflater.inflate(R.layout.tasks_list_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            onAddTask()
        }
        taskAdapter = TaskAdapter(list ?: arrayListOf(), {
            removeTask(it)
        }, onEditTask)
        recv1.adapter = taskAdapter
    }
    fun setList(list1:List<Task>){
        list = list1
    }

}