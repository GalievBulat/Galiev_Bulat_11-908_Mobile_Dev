package com.kakadurf.newProject.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kakadurf.newProject.dao.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title:String,
    var description:String)
{
    constructor(title: String, description: String): this(0,title, description)
}