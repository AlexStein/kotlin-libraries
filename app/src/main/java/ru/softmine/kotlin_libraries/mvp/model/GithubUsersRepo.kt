package ru.softmine.kotlin_libraries.mvp.model

import io.reactivex.rxjava3.core.Observable
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser

class GithubUsersRepo {

    fun getUser(id: Int) : GithubUser {
        return GithubUser("RepoUser $id")
    }

    fun getUsers(): Observable<GithubUser> {
        return Observable.range(1,20).map { getUser(it) }
    }
}