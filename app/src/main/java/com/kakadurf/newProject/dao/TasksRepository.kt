package com.kakadurf.newProject.dao

import com.kakadurf.newProject.entities.Task
@Deprecated(message = "old")
class TasksRepository(){
    private val list =  ArrayList<Task>()
    init {
        with(list){
            add(
                Task(
                    1,
                    "Kostyan",
                    "rer"
                )
            )
            add(
                Task(
                    2,
                    "Andrey",
                    "rerree"
                )
            )
            add(
                Task(
                    3,
                    "Sadam",
                    "No"
                )
            )
            add(
                Task(
                    4,
                    "Ivan",
                    "no more rerer"
                )
            )
        }
    }
    fun getAll():List<Task> {
        return list
    }
    fun findById(id:Int): Task?{
        if(list.size<id || id<1)
            return null
        return list[id-1]
    }
    fun add(task: Task){
        list.add(task)
        list.sortBy { u: Task ->u.id }
    }
    fun size() = list.size

}