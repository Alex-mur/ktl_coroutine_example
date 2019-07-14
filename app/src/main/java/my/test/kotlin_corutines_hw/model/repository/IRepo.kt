package my.test.kotlin_corutines_hw.model.repository

import kotlinx.coroutines.Deferred
import my.test.kotlin_corutines_hw.model.entity.Launch

interface IRepo {
    suspend fun getLastLaunches() : Deferred<List<Launch>>
    suspend fun getLaunch(launchNumber: Int) : Deferred<Launch>
}