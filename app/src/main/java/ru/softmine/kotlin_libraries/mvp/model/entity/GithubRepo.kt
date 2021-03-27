package ru.softmine.kotlin_libraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubRepo(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val description: String?,
    @Expose val fork: Boolean
) : Parcelable