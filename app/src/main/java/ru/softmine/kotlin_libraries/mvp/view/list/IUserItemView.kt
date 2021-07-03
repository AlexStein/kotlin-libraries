package ru.softmine.kotlin_libraries.mvp.view.list

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface IUserItemView : IItemView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLogin(text: String)
}