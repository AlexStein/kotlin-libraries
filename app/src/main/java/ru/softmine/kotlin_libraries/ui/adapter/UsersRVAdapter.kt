package ru.softmine.kotlin_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.softmine.kotlin_libraries.databinding.ItemUserBinding
import ru.softmine.kotlin_libraries.mvp.model.image.IImageLoader
import ru.softmine.kotlin_libraries.mvp.presenter.list.IUsersListPresenter
import ru.softmine.kotlin_libraries.mvp.view.list.UserItemView
import ru.softmine.kotlin_libraries.ui.App
import javax.inject.Inject

class UsersRVAdapter(
    private val presenter: IUsersListPresenter
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })


    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            textViewUser.text = text
        }

        override fun loadAvatar(url: String) = with(vb) {
            imageLoader.load(url, imageAvatar)
        }
    }
}