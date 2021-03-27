package ru.softmine.kotlin_libraries.mvp.navigation

import com.github.terrakok.cicerone.Screen
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser) : Screen
    fun repository(githubRepository: GithubRepo): Screen
}