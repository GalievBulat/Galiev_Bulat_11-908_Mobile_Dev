package com.kakadurf.newProject.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title:String,
    var description:String)
{
    constructor(title: String, description: String): this(-1,title, description)
}