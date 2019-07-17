package my.test.kotlin_coroutines_hw.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.test.kotlin_coroutines_hw.model.entity.DataWrapper
import my.test.kotlin_coroutines_hw.model.entity.Launch
import my.test.kotlin_coroutines_hw.model.repository.Repo
import java.lang.Exception

class LaunchViewModel : ViewModel() {
    private val repo = Repo
    private val launchLiveData = MutableLiveData<DataWrapper<Launch>>()

    fun getLaunch() : LiveData<DataWrapper<Launch>> {
        return launchLiveData
    }

    fun loadData(launchNumber : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                launchLiveData.postValue(
                    DataWrapper(repo.getLaunchAsync(launchNumber).await(), null)
                )

            } catch (e : Exception) {
                launchLiveData.postValue(
                    DataWrapper(launchLiveData.value?.data, e)
                )
            }
        }
    }
}
