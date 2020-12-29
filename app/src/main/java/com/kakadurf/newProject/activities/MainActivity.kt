package com.kakadurf.newProject.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kakadurf.newProject.R
import com.kakadurf.newProject.dao.TasksDB
import com.kakadurf.newProject.dao.TasksDao
import com.kakadurf.newProject.entities.Task
import com.kakadurf.newProject.fragments.TaskManipulatingFragment
import com.kakadurf.newProject.fragments.TasksListFragment
import com.kakadurf.newProject.interfaces.Promise
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

private const val ADDING_NEW_TASK_FLAG = -1
class MainActivity : AppCompatActivity(), CoroutineScope by MainScope(){
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
    private var db: TasksDB? = null
    private var dao: TasksDao? = null
    private var list: List<Task>?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            db = TasksDB.getInstance(applicationContext)
            dao = db?.dao()
            list = dao?.getList()?.also {
                renderFragment(buildTasksListFragment(it))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }
    private val addingFunction : (String, String)->Unit = { title, description->
        dao?.functionInBackground { add(Task(title, description)) }
        /*
        launch {
            dao?.add(Task(title, description))
            Log.d("hi","added")
        }*/
    }
    private val gettingFunction : (Int) -> Promise<Task?> = {
         Promise {async{
             dao?.get(it)
         }}
    }
    private val updatingFunction: (Task)->Unit = {
        dao?.functionInBackground { dao?.update(it) }
        /*launch {
            dao?.update(it)
        }*/
    }
    private val onExecutionListener:()->Unit = {
        list?.let {
            renderFragment(buildTasksListFragment(it))
        }
    }
    private fun renderFragment (fr: Fragment){
            val trans = supportFragmentManager.beginTransaction()
            with(trans) {
                replace(R.id.frl_frame, fr)
                commit()
            }
    }

    private fun buildTaskManipulationFragment(flag: Int) : TaskManipulatingFragment{
        return TaskManipulatingFragment(flag, addingFunction, gettingFunction,updatingFunction,onExecutionListener)
    }
    private fun buildTasksListFragment(list: List<Task>) : TasksListFragment =
        TasksListFragment(list, {i ->
            renderFragment(buildTaskManipulationFragment(i))
        },{
            renderFragment(buildTaskManipulationFragment(ADDING_NEW_TASK_FLAG))
        },{
            //dao.delete(it)
        })
    private fun TasksDao.functionInBackground(daoFun: TasksDao.()->Unit){
        launch {
            daoFun()
        }
    }

}

