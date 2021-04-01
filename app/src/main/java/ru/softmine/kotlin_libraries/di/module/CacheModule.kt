package ru.softmine.kotlin_libraries.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IUsersCache
import ru.softmine.kotlin_libraries.mvp.model.repo.UsersCache
import ru.softmine.kotlin_libraries.mvp.model.entity.room.db.Database
import ru.softmine.kotlin_libraries.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCache(db: Database): IUsersCache = UsersCache(db)

}