package ru.softmine.kotlin_libraries.ui

import android.app.Application
import ru.softmine.kotlin_libraries.di.AppComponent
import ru.softmine.kotlin_libraries.di.DaggerAppComponent
import ru.softmine.kotlin_libraries.di.module.AppModule
import ru.softmine.kotlin_libraries.mvp.model.entity.room.db.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}