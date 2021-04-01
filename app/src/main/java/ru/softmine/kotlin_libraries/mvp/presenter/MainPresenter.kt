package ru.softmine.kotlin_libraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.kotlin_libraries.mvp.navigation.IScreens
import ru.softmine.kotlin_libraries.mvp.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter() :
    MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }

}
