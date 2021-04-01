package ru.softmine.kotlin_libraries.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubUser
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomImage
import ru.softmine.kotlin_libraries.mvp.model.entity.room.dao.ImageDao
import ru.softmine.kotlin_libraries.mvp.model.entity.room.dao.RepositoryDao
import ru.softmine.kotlin_libraries.mvp.model.entity.room.dao.UserDao
import java.lang.IllegalStateException

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepo::class,
        RoomImage::class
    ],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw IllegalStateException("Database has not been created")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE `RoomImage` (`url` TEXT PRIMARY KEY NOT NULL, `fileName` TEXT NOT NULL)"
                )
            }
        }

    }

}
