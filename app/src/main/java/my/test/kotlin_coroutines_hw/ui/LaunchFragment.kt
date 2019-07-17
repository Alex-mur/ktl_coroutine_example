package my.test.kotlin_coroutines_hw.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.launch_fragment.*

import my.test.kotlin_coroutines_hw.R
import my.test.kotlin_coroutines_hw.viewmodel.LaunchViewModel

class LaunchFragment : Fragment() {

    companion object {
        const val LAUNCH_ID = "launch_id"
    }

    private lateinit var viewModel: LaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel(arguments!!.getInt(LAUNCH_ID))
    }

    private fun initViewModel(id: Int) {
        viewModel = ViewModelProviders.of(this).get(LaunchViewModel::class.java)
        viewModel.getLaunch().observe(this, Observer { data ->
            data?.data?.links?.mission_patch?.let {
                Picasso.get().load(it).into(iv_mission_patch_big)
            }

            tv_title.text = data?.data?.mission_name
            tv_rocket_name.text = data?.data?.rocket?.rocket_name
            tv_launch_date.text = data?.data?.launch_date_utc
            tv_site_name.text = data?.data?.launch_site?.site_name_long
            tv_launch_success.text = data?.data?.launch_success
            tv_details.text = data?.data?.details

            data?.data?.links?.flickr_images?.let {
                if (it.isNotEmpty()) {
                    Picasso.get().load(it[0]).into(iv_photo)
                } else {
                    iv_photo.visibility = View.GONE
                    tv_photo_desc.visibility = View.GONE
                }
            }
        })
        viewModel.loadData(id)
    }

}
