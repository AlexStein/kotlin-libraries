package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.kotlin_libraries.mvp.model.api.IDataSource
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomGithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.room.db.Database
import ru.softmine.kotlin_libraries.mvp.model.network.INetworkStatus
import java.lang.RuntimeException


class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
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
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.findByLogin(user.login)
                        ?: throw RuntimeException("No user in DB")
                    db.repositoryDao.findForUser(roomUser.id).map { roomRepo ->
                        GithubRepo(
                            roomRepo.id, roomRepo.name, roomRepo.description, roomRepo.fork
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())

}