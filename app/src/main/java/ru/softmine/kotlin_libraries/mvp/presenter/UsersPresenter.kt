package ru.softmine.kotlin_libraries.mvp.presenter

import android.widget.ImageView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.model.image.IImageLoader
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IGithubUsersRepo
import ru.softmine.kotlin_libraries.mvp.navigation.IScreens
import ru.softmine.kotlin_libraries.mvp.presenter.list.IUsersListPresenter
import ru.softmine.kotlin_libraries.mvp.view.UsersView
import ru.softmine.kotlin_libraries.mvp.view.list.UserItemView
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class UsersPresenter: MvpPresenter<UsersView>() {

    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var usersRepo: IGithubUsersRepo
    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val githubUser = users[view.pos]
            view.setLogin(githubUser.login)
            view.loadAvatar(githubUser.avatarUrl)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val githubUser = usersListPresenter.users[view.pos]
            router.navigateTo(screens.user(githubUser))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}