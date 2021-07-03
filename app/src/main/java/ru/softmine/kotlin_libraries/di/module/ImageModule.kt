package ru.softmine.kotlin_libraries.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.softmine.kotlin_libraries.mvp.model.image.IImageLoader
import ru.softmine.kotlin_libraries.mvp.model.network.INetworkStatus
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IImageCache
import ru.softmine.kotlin_libraries.ui.App
import ru.softmine.kotlin_libraries.ui.image.GlideImageLoader
import javax.inject.Named

@Module
class ImageModule {

    @Named("location")
    @Provides
    fun location(): String = App.imageLocation

    @Provides
    fun imageLoader(
        imageCache: IImageCache,
        networkStatus: INetworkStatus,
        @Named("location") location: String
    ): IImageLoader<ImageView> = GlideImageLoader(imageCache, networkStatus, location)
}