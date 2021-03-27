package ru.softmine.kotlin_libraries.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.navigation.IScreens
import ru.softmine.kotlin_libraries.ui.fragment.RepositoryFragment
import ru.softmine.kotlin_libraries.ui.fragment.UserFragment
import ru.softmine.kotlin_libraries.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(githubUser: GithubUser) =
        FragmentScreen { UserFragment.newInstance(githubUser) }
    override fun repository(githubRepository: GithubRepo) =
        FragmentScreen { RepositoryFragment.newInstance(githubRepository) }
}