package ru.softmine.kotlin_libraries.di.module

import dagger.Module
import dagger.Provides
import ru.softmine.kotlin_libraries.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app

}