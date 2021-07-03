package ru.softmine.kotlin_libraries.mvp.model.repo.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomImage
import ru.softmine.kotlin_libraries.mvp.model.entity.room.db.Database
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IImageCache
import java.io.File
import java.io.FileOutputStream
import java.util.*

class ImageCache(private val db: Database) : IImageCache {
    override fun putImage(url: String, location: String, byteArray: ByteArray): Completable {
        return Completable.create { emitter ->
            val file = File("$location/${UUID.randomUUID()}")
            try {
                FileOutputStream(file).use {
                    it.write(byteArray)
                }

            } catch (e: Exception) {
                emitter.onError(e)
            }
            val roomImage = RoomImage(url, file.absolutePath)
            db.imageDao.insert(roomImage)
            emitter.onComplete()
        }
    }

    override fun getImage(url: String): Single<ByteArray?> {
        return Single.fromCallable {
            db.imageDao.findByUrl(url)?.let {
                File(it.fileName).readBytes()
            }
        }
    }
}
