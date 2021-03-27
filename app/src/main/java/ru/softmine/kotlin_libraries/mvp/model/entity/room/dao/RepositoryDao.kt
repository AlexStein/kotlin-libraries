package ru.softmine.kotlin_libraries.mvp.model.entity.room.dao

import androidx.room.*
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubRepo

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomGithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGithubRepo>)

    @Update
    fun update(user: RoomGithubRepo)

    @Update
    fun update(vararg users: RoomGithubRepo)

    @Update
    fun update(users: List<RoomGithubRepo>)

    @Delete
    fun delete(user: RoomGithubRepo)

    @Delete
    fun delete(vararg users: RoomGithubRepo)

    @Delete
    fun delete(users: List<RoomGithubRepo>)

    @Query("SELECT * FROM RoomGithubRepo")
    fun getAll(): List<RoomGithubRepo>

    @Query("SELECT * FROM RoomGithubRepo WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGithubRepo>
    
}