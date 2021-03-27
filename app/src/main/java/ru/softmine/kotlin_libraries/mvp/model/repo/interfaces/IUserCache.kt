package ru.softmine.kotlin_libraries.mvp.model.repo.interfaces

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser

interface IUsersCache {
    fun putUsers(users: List<GithubUser>): Completable
    fun getUsers(): Single<List<GithubUser>>
}