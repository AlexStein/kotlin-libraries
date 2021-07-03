package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.kotlin_libraries.mvp.model.api.IDataSource
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.model.network.INetworkStatus
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IGithubUsersRepo
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IUsersCache

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val usersCache: IUsersCache
) : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    usersCache.putUsers(users).andThen(Single.just(users))
                }
            } else {
                usersCache.getUsers()
            }
        }.subscribeOn(Schedulers.io())

    override fun getUser(login: String): Single<GithubUser> =
        api.getUser(login).subscribeOn(Schedulers.io())

    override fun getUserRepos(user: String): Single<List<GithubRepo>> =
        api.getUserRepos(user).subscribeOn(Schedulers.io())
}