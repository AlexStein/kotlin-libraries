package ru.softmine.kotlin_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.softmine.kotlin_libraries.databinding.ItemRepoBinding
import ru.softmine.kotlin_libraries.mvp.presenter.list.IReposListPresenter
import ru.softmine.kotlin_libraries.mvp.view.list.IRepoItemView

class ReposRecycleViewAdapter(
    private val presenter: IReposListPresenter
) : RecyclerView.Adapter<ReposRecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRepoBinding.inflate(
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

    inner class ViewHolder(val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        IRepoItemView {
        override var pos = -1

        override fun setName(name: String) = with(vb) {
            textViewName.text = name
        }

        override fun setDescription(description: String?) = with(vb) {
            description.let { textViewDescription.text = it }
        }
    }
}