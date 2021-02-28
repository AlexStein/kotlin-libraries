package ru.softmine.kotlin_libraries.mvp.model

class CountersModel {

    val counters = mutableListOf(0, 0, 0)

    fun getCurrent(index: Int) = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }
}