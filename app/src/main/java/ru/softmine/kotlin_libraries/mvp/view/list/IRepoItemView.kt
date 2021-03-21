package ru.softmine.kotlin_libraries.mvp.view.list

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface IRepoItemView : IItemView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setName(name: String)
    fun setDescription(description: String?)
}