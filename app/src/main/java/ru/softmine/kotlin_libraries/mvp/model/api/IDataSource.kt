package ru.softmine.kotlin_libraries.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser


interface IDataSource {

    @GET("users")
    fun getUsers() : Single<List<GithubUser>>

    @GET("users")
    fun getUser(login: String) : Single<GithubUser>

//    @GET
//    fun getUserRepos(@Url url: String) : Single

}