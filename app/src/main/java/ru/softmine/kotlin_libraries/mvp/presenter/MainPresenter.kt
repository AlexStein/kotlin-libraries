package ru.softmine.kotlin_libraries.mvp.presenter

import ru.softmine.kotlin_libraries.R
import ru.softmine.kotlin_libraries.mvp.model.CountersModel
import ru.softmine.kotlin_libraries.mvp.view.MainView

class MainPresenter(val mainView: MainView) {
    val model = CountersModel()

    fun counterClick(id: Int){
        when(id){
            R.id.btn_counter1 -> {
                val nextValue = model.next(0)
                mainView.setButtonText(0, nextValue.toString())
            }
            R.id.btn_counter2 -> {
                val nextValue = model.next(1)
                mainView.setButtonText(1, nextValue.toString())
            }
            R.id.btn_counter3 -> {
                val nextValue = model.next(2)
                mainView.setButtonText(2, nextValue.toString())
            }
        }
    }
}