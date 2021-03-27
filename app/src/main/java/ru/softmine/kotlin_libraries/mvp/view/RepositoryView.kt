package ru.softmine.kotlin_libraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun init()
    fun setId(text: String)
    fun setTitle(text: String)
    fun setDescription(text: String)
}