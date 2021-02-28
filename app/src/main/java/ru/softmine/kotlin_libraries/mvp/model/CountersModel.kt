package ru.softmine.kotlin_libraries.mvp.model

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrent(index: Int) = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }
}