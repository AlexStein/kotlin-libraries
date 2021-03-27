package ru.softmine.kotlin_libraries.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.softmine.kotlin_libraries.mvp.model.entity.room.RoomImage
import ru.softmine.kotlin_libraries.mvp.model.entity.room.db.Database
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IImageCache
import java.io.File
import java.util.*

class ImageCache(private val db: Database) : IImageCache {
    override fun putImage(url: String, location: String, byteArray: ByteArray) {
        Single.fromCallable {
            val file = File("$location/${UUID.randomUUID()}")
            file.writeBytes(byteArray)

            val roomImage = RoomImage(url, file.absolutePath)
            db.imageDao.insert(roomImage)
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
