package com.kakadurf.newProject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.frame3.*

class Fragment3(): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.frame3,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapt = CardAdapter(listOf(CustomPagerAdapt(listOf(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background)),CustomPagerAdapt(listOf(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background))))
        rv_rv2.adapter = adapt
    }
}
