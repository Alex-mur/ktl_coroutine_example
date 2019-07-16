package my.test.kotlin_coroutines_hw.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_fragment.*

import my.test.kotlin_coroutines_hw.R
import my.test.kotlin_coroutines_hw.viewmodel.ItemViewModel

class ItemFragment : Fragment() {

    private lateinit var viewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        viewModel.getLaunch().observe(this, Observer { data ->
            tv_test.text = data?.e?.message ?: data?.data?.mission_name
        })
        viewModel.loadData(10)
    }

}
