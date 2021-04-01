package ru.softmine.kotlin_libraries.mvp.model.repo.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.room.db.Database
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IRepositoriesCache

class RepositoriesCache(private val db: Database) : IRepositoriesCache {

    override fun putRepositories(
        user: GithubUser,
        repositories: List<GithubRepo>
    ): Completable {
        return Completable.fromAction {
            Single.fromCallable {
                val roomUser = db.userDao.findByLogin(user.login)
                    ?: throw RuntimeException("No user in DB")
                val roomRepos = repositories.map { repo ->
                    RoomGithubRepo(
                        repo.id,
                        repo.name,
                        repo.description,
                        repo.fork,
                        roomUser.id
                    )
                }
                db.repositoryDao.insert(roomRepos)
                repositories
            }
        }
    }

    override fun getRepositories(user: GithubUser): Single<List<GithubRepo>> {
        return Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login)
                ?: throw RuntimeException("No user in DB")
            db.repositoryDao.findForUser(roomUser.id).map { roomRepo ->
                GithubRepo(
                    roomRepo.id, roomRepo.name, roomRepo.description, roomRepo.fork
                )
            }
        }
    }
}