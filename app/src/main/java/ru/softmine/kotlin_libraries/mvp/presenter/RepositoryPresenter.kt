package ru.softmine.kotlin_libraries.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.view.RepositoryView

class RepositoryPresenter(private val router: Router, private val githubRepository: GithubRepo) :
    MvpPresenter<RepositoryView>() {

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
