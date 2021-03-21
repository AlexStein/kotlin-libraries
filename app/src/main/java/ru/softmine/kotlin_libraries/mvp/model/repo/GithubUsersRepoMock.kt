package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser

class GithubUsersRepoMock {

    private val users = listOf(
        GithubUser("login1", "", ""),
        GithubUser("login2", "", ""),
        GithubUser("login3", "", ""),
        GithubUser("login4", "", ""),
        GithubUser("login5", "", ""),
        GithubUser("login6", "", ""),
        GithubUser("login7", "", ""),
        GithubUser("login8", "", ""),
        GithubUser("login9", "", ""),
        GithubUser("login10", "", ""),
    )

    fun getUser(id: Int) : GithubUser {
        return GithubUser("RepoUser $id", "", "")
    }

    fun getUsers() = Observable.fromCallable {
        users
    }.subscribeOn(Schedulers.io())
}