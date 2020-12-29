package com.kakadurf.newProject.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kakadurf.newProject.entities.Task

const val DATABASE_NAME = "my_app_name.db"
@Database(entities = [Task::class], version = 63)
abstract class TasksDB: RoomDatabase() {
    companion object {
        private var sInstance: TasksDB? = null
        @Synchronized
        fun  getInstance( context: Context): TasksDB {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context,
                    TasksDB::class.java, DATABASE_NAME
                )
                    //.allowMainThreadQueries() // буду бить
                    //.fallbackToDestructiveMigration()
                    //.createFromFile(File("tasks"))
                    //.createFromAsset("database/tasks.db")
                    .build()
            }
            return sInstance as TasksDB
        }
    }
    abstract fun dao(): TasksDao

}