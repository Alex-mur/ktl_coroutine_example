package my.test.kotlin_coroutines_hw.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.launch_list_fragment.*

import my.test.kotlin_coroutines_hw.R
import my.test.kotlin_coroutines_hw.ui.adapter.LaunchListRVAdapter
import my.test.kotlin_coroutines_hw.viewmodel.LaunchListViewModel

class LaunchListFragment : Fragment() {

    private lateinit var viewModelLaunchList: LaunchListViewModel
    private lateinit var rvAdapter : LaunchListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    private fun initRV() {
        rvAdapter = LaunchListRVAdapter(viewModelLaunchList.launchListRVPresenter)
        rv_launch_list.layoutManager = LinearLayoutManager(context)
        rv_launch_list.adapter = rvAdapter
    }

    private fun initViewModel() {
        viewModelLaunchList = ViewModelProviders.of(this).get(LaunchListViewModel::class.java)
        viewModelLaunchList.getLaunchList().observe(this, Observer { data ->
            data?.e?.message?.let { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
            data?.data?.let { rvAdapter.notifyDataSetChanged() }
        })
        initRV()
        viewModelLaunchList.loadData()
    }
}
