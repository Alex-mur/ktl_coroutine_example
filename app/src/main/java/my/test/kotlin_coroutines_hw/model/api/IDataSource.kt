package my.test.kotlin_coroutines_hw.model.api

import kotlinx.coroutines.Deferred
import my.test.kotlin_coroutines_hw.model.entity.Launch
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {

    @GET("/v3/launches/past")
    fun getLastLaunches() : Deferred<List<Launch>>

    @GET("/v3/launches/{launchNumber}")
    fun getLaunch(@Path("launchNumber") launchNumber : Int) : Deferred<Launch>
}