package com.kakadurf.newProject.dao

import androidx.room.*
import com.kakadurf.newProject.entities.Task

@Dao
interface TasksDao {
    @Insert
    fun add(task: Task)

    @Query("SELECT id,title,description FROM task WHERE id = :id")
    fun get(id: Int): Task

    @Update
    fun update(task: Task)

    /*@Delete
    fun delete(id: Int)*/
    //@Delete()

    @Delete
    fun delete(task: Task)

    @Query("SELECT id,title,description FROM task")
    fun getList(): List<Task>

}