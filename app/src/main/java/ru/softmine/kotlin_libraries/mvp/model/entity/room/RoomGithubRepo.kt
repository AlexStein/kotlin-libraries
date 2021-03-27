package ru.softmine.kotlin_libraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepo(
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val fork: Boolean,
    var userId: String
)

