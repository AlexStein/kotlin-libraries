package ru.softmine.kotlin_libraries.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.softmine.kotlin_libraries.mvp.navigation.IScreens
import ru.softmine.kotlin_libraries.ui.navigation.AndroidScreens
import javax.inject.Singleton

@Module
class CiceroneModule {

    val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()

}