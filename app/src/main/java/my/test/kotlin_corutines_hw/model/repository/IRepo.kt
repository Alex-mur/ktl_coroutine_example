package my.test.kotlin_corutines_hw.model.repository

import kotlinx.coroutines.Deferred
import my.test.kotlin_corutines_hw.model.entity.Launch

interface IRepo {
    suspend fun getLastLaunchesAsync() : Deferred<List<Launch>>
    suspend fun getLaunchAsync(launchNumber: Int) : Deferred<Launch>
}