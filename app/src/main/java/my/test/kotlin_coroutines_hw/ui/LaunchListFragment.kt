package my.test.kotlin_coroutines_hw.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.launch_list_fragment.*

import my.test.kotlin_coroutines_hw.R
import my.test.kotlin_coroutines_hw.viewmodel.LaunchListViewModel
import java.lang.StringBuilder

class LaunchListFragment : Fragment() {

    private lateinit var viewModelLaunch: LaunchListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelLaunch = ViewModelProviders.of(this).get(LaunchListViewModel::class.java)
        viewModelLaunch.getLaunchList().observe(this, Observer { data ->
            if (data?.e != null) {
                tv_test.text = data.e.message
            } else {
                val sb = StringBuilder()
                data?.data?.forEach { sb.append(it.mission_name) }
                tv_test.text = sb.toString()
            }
        })
        viewModelLaunch.loadData()

        btn_next.setOnClickListener { findNavController().navigate(R.id.action_listFragment_to_itemFragment) }

    }

}
