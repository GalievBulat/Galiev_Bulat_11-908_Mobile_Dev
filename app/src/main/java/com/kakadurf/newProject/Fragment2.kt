package com.kakadurf.newProject

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.alert.view.*
import kotlinx.android.synthetic.main.frame2.*
import java.lang.RuntimeException

class Fragment2(): Fragment() {
    var userAdapter: UserAdapter?= null
    lateinit var main: View
    val repository = UserRepository()
    lateinit var inflater:LayoutInflater
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        main = inflater.inflate(R.layout.alert,null)
        this.inflater = inflater
        return inflater.inflate(R.layout.frame2,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
                /*val parent = main.parent as ViewGroup
                parent.removeView(main)*/
                main = View.inflate(context,R.layout.alert,null)
                AlertDialog.Builder(context)
                .setTitle("Adding")
                .setMessage("What to add?")
                .setPositiveButton("Ok") { _, _ ->
                    addV(main.et_highlight,main.et_desc,main.et_pos)
                    userAdapter?.notifyDataSetChanged()
                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(main)
                .show()
        }
        val list =repository.getAll() as ArrayList
        userAdapter = UserAdapter(list){
            for(i in 0 until list.size){
               if(list[i].id == it) {
                   list.remove(list[i])
                   break
               }
            }
            userAdapter?.notifyDataSetChanged()
        }
        recv1.adapter = userAdapter
    }

    fun  addV(et_highlight : EditText,
             et_desc : EditText,
                     et_pos :EditText){
        val highlight = et_highlight.text.toString()
        val desc = et_desc.text.toString()
        var pos = et_pos.text.toString().toInt()
        if (pos>repository.size())
            pos = repository.size()
        if (pos<0){
            pos = 0
        }
        val list =repository.getAll() as ArrayList
         for(i in 1 until list.size) if(list[i].id == pos) {
            return
         }
        repository.add(User(pos,highlight,desc,"hobo"))
    }
}