package com.kakadurf.newProject.helper

import com.kakadurf.newProject.interfaces.Promise
import kotlinx.coroutines.Deferred

class CoroutinePromise<T>(promise: ()->Deferred<T>) :Promise<T> {
    private val duty : Deferred<T> = promise()
    override fun getWhatWasPromised(): T? =
        if (duty.isCompleted) duty.getCompleted() else null
}