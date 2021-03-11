package ru.softmine.kotlin_libraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.view.UserView

@InjectViewState
class UserPresenter(
    private val githubUser: GithubUser,
    private val router: Router
) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(githubUser.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}