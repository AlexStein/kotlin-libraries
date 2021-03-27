package ru.softmine.kotlin_libraries.ui.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.softmine.kotlin_libraries.R
import ru.softmine.kotlin_libraries.mvp.model.image.IImageLoader
import ru.softmine.kotlin_libraries.mvp.model.repo.interfaces.IImageCache
import java.io.ByteArrayOutputStream


class GlideImageLoader : IImageLoader<ImageView> {

    override fun load(url: String, container: ImageView, imageCache: IImageCache) {

        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageCache.getImage(url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ byteArray ->
                            byteArray?.let {
                                container.setImageBitmap(
                                    BitmapFactory.decodeByteArray(
                                        byteArray,0,byteArray.size
                                    )
                                )
                            }
                        }, {
                            Log.e("GlideImageLoader", it.message.toString())
                        })
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let { bmp ->
                        val location: String = container.context.filesDir.path.toString()
                        val stream = ByteArrayOutputStream()
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        val byteArray: ByteArray = stream.toByteArray()

                        try {
                            imageCache.putImage(url, location, byteArray)
                        } catch (e: Exception) {
                            Log.d("GlideImageLoader", e.message.toString())
                        }
                    }
                    return false
                }
            })
            .into(container)
    }

}