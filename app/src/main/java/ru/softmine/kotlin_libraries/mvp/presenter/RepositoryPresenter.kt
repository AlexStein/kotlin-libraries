package ru.softmine.kotlin_libraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.view.RepositoryView
import javax.inject.Inject

class RepositoryPresenter(private val githubRepository: GithubRepo) :
    MvpPresenter<RepositoryView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id)
        viewState.setTitle(githubRepository.name)
        viewState.setDescription(githubRepository.description ?: "")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
