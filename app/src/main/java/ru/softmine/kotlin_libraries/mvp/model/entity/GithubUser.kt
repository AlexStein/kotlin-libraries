package ru.softmine.kotlin_libraries.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(val login: String) : Parcelable {
    @IgnoredOnParcel
    val fullName = "Full name of $login"
}