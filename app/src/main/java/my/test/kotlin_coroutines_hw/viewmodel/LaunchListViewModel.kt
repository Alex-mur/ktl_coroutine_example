package my.test.kotlin_coroutines_hw.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.test.kotlin_coroutines_hw.model.entity.DataWrapper
import my.test.kotlin_coroutines_hw.model.entity.Launch
import my.test.kotlin_coroutines_hw.model.repository.IRepo
import my.test.kotlin_coroutines_hw.model.repository.Repo
import java.lang.Exception


class LaunchListViewModel : ViewModel() {

    val launchListRVPresenter: ILaunchListRVPresenter = LaunchListRVPresenter()
    private val repo : IRepo = Repo
    private val launchListLiveData = MutableLiveData<DataWrapper<List<Launch>>>()

    inner class LaunchListRVPresenter : ILaunchListRVPresenter{
        override fun onItemClick(rowView: ILaunchListRVItemView) {
            launchListLiveData.value?.data?.get(rowView.getPos())?.flight_number?.let {
                rowView.openLaunchScreen(it)
            }
        }

        override fun bindView(rowView: ILaunchListRVItemView) {
            rowView.setImage(launchListLiveData.value?.data?.get(rowView.getPos())?.links?.mission_patch_small)
            rowView.setTitle(launchListLiveData.value?.data?.get(rowView.getPos())?.mission_name)
            rowView.setYear(launchListLiveData.value?.data?.get(rowView.getPos())?.launch_year)
        }

        override fun getItemsCount(): Int {
            return launchListLiveData.value?.data?.size ?: 0
        }

    }

    fun getLaunchList() : LiveData<DataWrapper<List<Launch>>> {
        return launchListLiveData
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                launchListLiveData.postValue(
                    DataWrapper(repo.getLastLaunchesAsync().await(),null)
                )

            } catch (e : Exception) {
                launchListLiveData.postValue(
                    DataWrapper(launchListLiveData.value?.data, e)
                )
            }
        }
    }

}
