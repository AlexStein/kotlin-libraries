package ru.softmine.kotlin_libraries.mvp.presenter

import ru.softmine.kotlin_libraries.mvp.model.CountersModel
import ru.softmine.kotlin_libraries.mvp.view.MainView

class MainPresenter(private val mainView: MainView) {
    private val model = CountersModel()

    fun counter1Click() {
        val nextValue = model.next(0)
        mainView.setButton1Text(nextValue.toString())
    }

    fun counter2Click() {
        val nextValue = model.next(1)
        mainView.setButton2Text(nextValue.toString())
    }

    fun counter3Click() {
        val nextValue = model.next(2)
        mainView.setButton3Text(nextValue.toString())
    }
}