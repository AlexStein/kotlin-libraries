package ru.softmine.kotlin_libraries.mvp.model.repo.interfaces

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser

interface IRepositoriesCache {
    fun putRepositories(user: GithubUser, repositories: List<GithubRepo>): Completable
    fun getRepositories(user: GithubUser): Single<List<GithubRepo>>
}