package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubUser
import ru.softmine.kotlin_libraries.mvp.model.entity.room.db.Database
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IUsersCache

class UsersCache(private val db: Database): IUsersCache {
    override fun putUsers(users: List<GithubUser>): Completable {
        return Completable.fromAction {
            Single.fromCallable {
                val roomUsers = users.map { user ->
                    RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
                }
                db.userDao.insert(roomUsers)
                users
            }
        }
    }

    override fun getUsers(): Single<List<GithubUser>> {
        return Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }
    }
}