package ru.softmine.kotlin_libraries.di

import dagger.Component
import ru.softmine.kotlin_libraries.di.module.*
import ru.softmine.kotlin_libraries.mvp.presenter.MainPresenter
import ru.softmine.kotlin_libraries.mvp.presenter.RepositoryPresenter
import ru.softmine.kotlin_libraries.mvp.presenter.UserPresenter
import ru.softmine.kotlin_libraries.mvp.presenter.UsersPresenter
import ru.softmine.kotlin_libraries.ui.activity.MainActivity
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CacheModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}