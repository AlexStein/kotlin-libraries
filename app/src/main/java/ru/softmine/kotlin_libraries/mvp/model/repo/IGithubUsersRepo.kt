package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser


interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUser(login: String): Single<GithubUser>
}