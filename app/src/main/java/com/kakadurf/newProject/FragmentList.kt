package com.kakadurf.newProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.frame_list.*

class FragmentList(private val musicPlayingController: MusicPlayingController): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.frame_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository =  MusicRepository()
        rv_main.adapter = MusicAdapter(repository.getAll(),musicPlayingController)
        rv_main.layoutManager = LinearLayoutManager(context)
    }
}