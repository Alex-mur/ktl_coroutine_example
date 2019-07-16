package my.test.kotlin_coroutines_hw.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.list_fragment.*

import my.test.kotlin_coroutines_hw.R
import my.test.kotlin_coroutines_hw.viewmodel.ListViewModel
import java.lang.StringBuilder

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.getLaunchList().observe(this, Observer { data ->
            if (data?.e != null) {
                tv_test.text = data.e.message
            } else {
                val sb = StringBuilder()
                data?.data?.forEach { sb.append(it.mission_name) }
                tv_test.text = sb.toString()
            }
        })
        viewModel.loadData()

        btn_next.setOnClickListener { findNavController().navigate(R.id.action_listFragment_to_itemFragment) }

    }

}
