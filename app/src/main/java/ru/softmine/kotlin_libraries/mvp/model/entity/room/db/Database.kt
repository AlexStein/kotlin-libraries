package ru.softmine.kotlin_libraries.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubUser
import ru.softmine.kotlin_libraries.mvp.model.entity.room.dao.RepositoryDao
import ru.softmine.kotlin_libraries.mvp.model.entity.room.dao.UserDao
import java.lang.IllegalStateException

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepo::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw IllegalStateException("Database has not been created")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }
        }
    }
}
