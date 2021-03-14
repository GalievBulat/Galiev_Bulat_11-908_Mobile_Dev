package com.kakadurf.hw_sem2.presentation.controllers

import com.kakadurf.hw_sem2.presentation.models.SpotDTO
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ListMvpView: MvpView {
    fun onDataFetch (list: List<SpotDTO>)
    fun onSearchDataFetch (id: Int)
    fun checkPerms()
}