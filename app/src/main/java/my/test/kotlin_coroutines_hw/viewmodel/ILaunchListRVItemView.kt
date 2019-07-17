package my.test.kotlin_coroutines_hw.viewmodel

interface ILaunchListRVItemView {
    fun getPos() : Int
    fun setImage(imgPath : String?)
    fun setTitle(text : String?)
    fun setYear(text : String?)
    fun openLaunchScreen(id: Int)
}