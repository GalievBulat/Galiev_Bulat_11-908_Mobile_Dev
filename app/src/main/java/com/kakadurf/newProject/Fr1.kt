package com.kakadurf.newProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frame1.*

class Fr1: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.frame1,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository =  MusicRepository()
        MainActivity.api?.let {
        MainActivity.musicAdapter = MusicAdapter(repository.getAll(), it) }
        rv_main.adapter = MainActivity.musicAdapter
        rv_main.layoutManager = LinearLayoutManager(context)
    }
}