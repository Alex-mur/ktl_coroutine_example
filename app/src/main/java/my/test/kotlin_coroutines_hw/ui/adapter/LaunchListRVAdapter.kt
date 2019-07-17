package my.test.kotlin_coroutines_hw.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.launch_list_fragment__rv_item.view.*
import my.test.kotlin_coroutines_hw.R
import my.test.kotlin_coroutines_hw.viewmodel.ILaunchListRVItemView
import my.test.kotlin_coroutines_hw.viewmodel.ILaunchListRVPresenter

class LaunchListRVAdapter (val presenter : ILaunchListRVPresenter)  : RecyclerView.Adapter<LaunchListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parentViewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parentViewGroup.context).inflate(R.layout.launch_list_fragment__rv_item, parentViewGroup, false))
    }

    override fun getItemCount(): Int {
        return presenter.getItemsCount()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position : Int) {
        viewHolder.id = position
        presenter.bindView(viewHolder)
        viewHolder.itemView.setOnClickListener { presenter.onItemClick(position) }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ILaunchListRVItemView {

        var id = 0

        override fun getPos(): Int {
            return id
        }

        override fun setImage(imgPath: String?) {
            imgPath?.let { Picasso.get().load(imgPath).into(itemView.rv_item__iv_logo) }
        }

        override fun setTitle(text: String) {
            itemView.rv_item__tv_text.text = text
        }

    }
}