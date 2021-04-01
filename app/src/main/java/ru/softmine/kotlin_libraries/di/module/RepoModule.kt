package ru.softmine.kotlin_libraries.di.module

import dagger.Module
import dagger.Provides
import ru.softmine.kotlin_libraries.mvp.model.api.IDataSource
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IUsersCache
import ru.softmine.kotlin_libraries.mvp.model.network.INetworkStatus
import ru.softmine.kotlin_libraries.mvp.model.repo.RetrofitGithubRepositoriesRepo
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IGithubUsersRepo
import ru.softmine.kotlin_libraries.mvp.model.repo.RetrofitGithubUsersRepo
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IGithubRepositoriesRepo
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IRepositoriesCache
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

}