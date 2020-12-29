package com.kakadurf.newProject.interfaces

import kotlinx.coroutines.Deferred

class Promise<T>(promise: ()->Deferred<T>) {
    private val duty : Deferred<T> = promise()
    fun getWhatWasPromised(): T =
        duty.getCompleted()
}