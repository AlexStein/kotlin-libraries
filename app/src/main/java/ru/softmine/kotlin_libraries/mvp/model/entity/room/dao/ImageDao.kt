package ru.softmine.kotlin_libraries.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomImage

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomImage)

    @Query("SELECT * FROM RoomImage WHERE url = :url LIMIT 1")
    fun findByUrl(url: String): RoomImage?
}