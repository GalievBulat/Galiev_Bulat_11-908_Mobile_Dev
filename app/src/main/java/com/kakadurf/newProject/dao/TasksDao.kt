package com.kakadurf.newProject.dao

import androidx.room.*
import com.kakadurf.newProject.entities.Task
const val TABLE_NAME = "task"
@Dao
interface TasksDao {
    @Insert
    fun add(task: Task)

    @Query("SELECT id,title,description FROM $TABLE_NAME WHERE id = :id")
    fun get(id: Int): Task

    @Update
    fun update(task: Task)

    /*@Delete
    fun delete(id: Int)*/
    //@Delete()


    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT id,title,description FROM task")
    fun getList(): List<Task>

}