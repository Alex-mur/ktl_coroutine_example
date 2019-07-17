package my.test.kotlin_coroutines_hw.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.launch_fragment.*

import my.test.kotlin_coroutines_hw.R
import my.test.kotlin_coroutines_hw.viewmodel.LaunchViewModel

class LaunchFragment : Fragment() {

    private lateinit var viewModel: LaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LaunchViewModel::class.java)
        viewModel.getLaunch().observe(this, Observer { data ->
            tv_test.text = data?.e?.message ?: data?.data?.mission_name
        })
        viewModel.loadData(10)
    }

}
