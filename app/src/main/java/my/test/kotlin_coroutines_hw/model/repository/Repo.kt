package my.test.kotlin_coroutines_hw.model.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import my.test.kotlin_coroutines_hw.model.api.IDataSource
import my.test.kotlin_coroutines_hw.model.entity.Launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repo : IRepo {
    private val dataSource : IDataSource = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IDataSource::class.java)

    override suspend fun getLastLaunchesAsync(): Deferred<List<Launch>> {
        return dataSource.getLastLaunches()
    }

    override suspend fun getLaunchAsync(launchNumber: Int): Deferred<Launch> {
        return dataSource.getLaunch(launchNumber)
    }

}
