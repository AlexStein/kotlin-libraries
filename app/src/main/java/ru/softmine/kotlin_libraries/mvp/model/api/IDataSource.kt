package ru.softmine.kotlin_libraries.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser


interface IDataSource {

    @GET("users")
    fun getUsers() : Single<List<GithubUser>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String) : Single<GithubUser>

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String) : Single<List<GithubRepo>>
}