package my.test.kotlin_coroutines_hw.viewmodel

interface ILaunchListRVPresenter {
    fun onItemClick(position : Int)
    fun bindView(rowView: ILaunchListRVItemView)
    fun getItemsCount(): Int
}