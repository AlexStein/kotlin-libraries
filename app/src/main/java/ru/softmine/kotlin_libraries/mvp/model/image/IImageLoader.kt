package ru.softmine.kotlin_libraries.mvp.model.image

import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IImageCache

interface IImageLoader<T> {
    fun load(url: String, container: T, imageCache: IImageCache)
}