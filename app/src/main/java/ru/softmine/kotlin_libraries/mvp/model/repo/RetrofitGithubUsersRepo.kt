package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.kotlin_libraries.mvp.model.api.IDataSource
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser


class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUser(login: String) = api.getUser(login).subscribeOn(Schedulers.io())
}