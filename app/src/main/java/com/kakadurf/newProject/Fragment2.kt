package com.kakadurf.newProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frame2.*

class Fragment2: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fab.setOnClickListener {
            //TODO
        }
        val repository = UserRepository()
        val userAdapter =  UserAdapter(repository.getAll()){
            //sendRedirect(it)
        }
        recyclerView.adapter = userAdapter
        return inflater.inflate(R.layout.frame2,container,false)
    }
}