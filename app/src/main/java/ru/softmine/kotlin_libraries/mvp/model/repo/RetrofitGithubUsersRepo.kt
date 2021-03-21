package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.kotlin_libraries.mvp.model.api.IDataSource


class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUser(user: String) = api.getUser(user).subscribeOn(Schedulers.io())
    override fun getUserRepos(user: String) = api.getUserRepos(user).subscribeOn(Schedulers.io())
}