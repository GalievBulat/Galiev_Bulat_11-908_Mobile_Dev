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
import com.kakadurf.newProject.helper.CoroutinePromise
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
    private var tasksListFragment: TasksListFragment? = null
    private var tasksManipulatingFragment: TaskManipulatingFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            db = TasksDB.getInstance(applicationContext)
            dao = db?.dao()
            list = dao?.getList()
            tasksListFragment = buildTasksListFragment()?.also { renderFragment(it) }
        }
        tasksManipulatingFragment = buildTaskManipulationFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }
    private val addingFunction : (String, String)->Unit = { title, description->
        dao?.functionInBackground { add(Task(title, description)) }
    }
    private val gettingFunction : (Int) -> Promise<Task?> = {
        CoroutinePromise {
            async {
                dao?.get(it)
            }
        }
    }
    private val updatingFunction: (Task)->Unit = {
        dao?.functionInBackground { dao?.update(it) }
    }
    private val onItemButtonListener: ()->Unit ={
        launch {
            list = dao?.getList()?.also {
                tasksListFragment?.let { renderFragment(it) }
            }
        }
        listUpdateFunction()
    }
    private val listUpdateFunction: ()->Unit = {
        launch {
            list = dao?.getList()
            tasksListFragment?.run {
                list?.let {setList(it) }
                runOnUiThread {
                    taskAdapter?.notifyDataSetChanged()
                }
            }
        }

    }
    private fun renderFragment (fr: Fragment){
        val trans = supportFragmentManager.beginTransaction()
        with(trans) {
            replace(R.id.frl_frame, fr)
            commit()
        }
    }

    private fun buildTaskManipulationFragment() : TaskManipulatingFragment{
        return TaskManipulatingFragment(
            addingFunction,
            gettingFunction,
            updatingFunction,
            onItemButtonListener)
    }
    private fun buildTasksListFragment() : TasksListFragment? =
            TasksListFragment(list, { i ->
                tasksManipulatingFragment?.run {
                    setFlag(i)
                    renderFragment(this)
                }
            }, {
                tasksManipulatingFragment?.run {
                    setFlag(ADDING_NEW_TASK_FLAG)
                    renderFragment(this)
                }
            }, {
                dao?.functionInBackground {
                    deleteById(it)
                }
                listUpdateFunction()
            })
    private fun TasksDao.functionInBackground(daoFun: TasksDao.()->Unit){
        launch {
            daoFun()
        }
    }

}

