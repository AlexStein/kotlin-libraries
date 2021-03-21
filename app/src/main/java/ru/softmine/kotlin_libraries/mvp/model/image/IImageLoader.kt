package ru.softmine.kotlin_libraries.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}