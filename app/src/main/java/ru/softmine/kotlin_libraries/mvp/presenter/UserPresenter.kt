package ru.softmine.kotlin_libraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IGithubRepositoriesRepo
import ru.softmine.kotlin_libraries.mvp.navigation.IScreens
import ru.softmine.kotlin_libraries.mvp.presenter.list.IReposListPresenter
import ru.softmine.kotlin_libraries.mvp.view.UserView
import ru.softmine.kotlin_libraries.mvp.view.list.IRepoItemView
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class UserPresenter(private val githubUser: GithubUser) : MvpPresenter<UserView>() {

    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var repositoriesRepo: IGithubRepositoriesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class ReposListPresenter : IReposListPresenter {
        val repos = mutableListOf<GithubRepo>()

        override var itemClickListener: ((IRepoItemView) -> Unit)? = null
        override fun getCount() = repos.size
        override fun bindView(view: IRepoItemView) {
            val githubRepo = repos[view.pos]
            view.setName(githubRepo.name)
            view.setDescription(githubRepo.description)
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(githubUser.login)
        viewState.initList()
        loadReposList()

        reposListPresenter.itemClickListener = { itemView ->
            val repository = reposListPresenter.repos[itemView.pos]
            router.navigateTo(screens.repository(repository))
        }
    }

    private fun loadReposList() {
        repositoriesRepo.getRepositories(githubUser)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.loadReposList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}