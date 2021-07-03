package ru.softmine.kotlin_libraries.mvp.model.repo.interfaces

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun putImage(url: String, location: String, byteArray: ByteArray): Completable
    fun getImage(url: String): Single<ByteArray?>
}
