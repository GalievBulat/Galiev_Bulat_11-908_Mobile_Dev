package com.kakadurf.newProject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kakadurf.newProject.R
import com.kakadurf.newProject.entities.Task
import com.kakadurf.newProject.interfaces.Promise
import kotlinx.android.synthetic.main.task_manipulating_fragment.*

class TaskManipulatingFragment(private val elementId: Int,
                               private val onAdd: (String, String)->Unit,
                               private val onGet: (Int)-> Promise<Task?>,
                               private val onUpdate: (Task)->Unit,
                               private val onButtonClicked: ()->Unit): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.task_manipulating_fragment,container,false)
    }
/*
    private fun addV(et_highlight : EditText,
                     et_desc : EditText,
                     et_pos : EditText
    ){
        val highlight = et_highlight.text.toString()
        val desc = et_desc.text.toString()
        var pos:Int
        pos = if (et_pos.text.toString()!="") et_pos.text.toString().toInt()
        else repository.size()+1
        if (pos>repository.size()+1)
            pos = repository.size()+1
        if (pos<0){
            pos = 0
        }
        println(pos)
        val list =repository.getAll() as ArrayList
        for(i in 1 until list.size) if(list[i].id == pos) {
            return
        }
        repository.add(
            Task(
                pos,
                highlight,
                desc
            )
        )
    }
*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(elementId == -1){
            bn_confirm.setOnClickListener {
                onAdd(et_title.text.toString(),et_desc.text.toString())
                onButtonClicked()
            }
        }else{
            val promise = onGet(elementId)
            bn_confirm.setOnClickListener {
                val task = promise.getWhatWasPromised()
                if (task == null){
                    Log.d("hi","wtf")
                }
                task?.let {
                    task.title = et_title.text.toString()
                    task.description = et_desc.text.toString()
                    onUpdate(it)
                    onButtonClicked()
                }
            }
        }
    }
}