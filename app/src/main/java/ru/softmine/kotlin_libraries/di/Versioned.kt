package ru.softmine.kotlin_libraries.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Versioned(val version: Int)