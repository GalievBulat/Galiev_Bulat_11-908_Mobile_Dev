package com.kakadurf.newProject.interfaces

interface Promise<T> {
    fun getWhatWasPromised(): T?
}