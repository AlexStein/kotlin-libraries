package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.kotlin_libraries.mvp.model.api.IDataSource
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.model.network.INetworkStatus
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IGithubRepositoriesRepo
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IRepositoriesCache


class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val reposCache: IRepositoriesCache
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getRepositories(url).flatMap { repositories ->
                        reposCache.putRepositories(user, repositories)
                            .andThen(Single.just(repositories))
                    }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                reposCache.getRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}