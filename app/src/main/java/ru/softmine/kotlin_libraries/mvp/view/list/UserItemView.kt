package ru.softmine.kotlin_libraries.mvp.view.list

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserItemView : IUserItemView {

}
