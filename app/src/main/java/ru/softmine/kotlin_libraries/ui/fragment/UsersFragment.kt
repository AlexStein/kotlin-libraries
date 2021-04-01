package ru.softmine.kotlin_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.kotlin_libraries.databinding.FragmentUsersBinding
import ru.softmine.kotlin_libraries.mvp.presenter.UsersPresenter
import ru.softmine.kotlin_libraries.mvp.view.UsersView
import ru.softmine.kotlin_libraries.ui.App
import ru.softmine.kotlin_libraries.ui.BackClickListener
import ru.softmine.kotlin_libraries.ui.adapter.UsersRVAdapter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentUsersBinding? = null
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        adapter?.let {
            App.instance.appComponent.inject(it)
        }

        vb?.recyclerViewUsers?.layoutManager = LinearLayoutManager(context)
        vb?.recyclerViewUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}