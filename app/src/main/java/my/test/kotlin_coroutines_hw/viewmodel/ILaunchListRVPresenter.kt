package my.test.kotlin_coroutines_hw.viewmodel

interface ILaunchListRVPresenter {
    fun onItemClick(rowView: ILaunchListRVItemView)
    fun bindView(rowView: ILaunchListRVItemView)
    fun getItemsCount(): Int
}