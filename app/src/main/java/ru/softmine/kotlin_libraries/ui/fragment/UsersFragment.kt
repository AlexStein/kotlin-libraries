package ru.softmine.kotlin_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.kotlin_libraries.databinding.FragmentUsersBinding
import ru.softmine.kotlin_libraries.mvp.model.api.ApiHolder
import ru.softmine.kotlin_libraries.mvp.model.repo.RetrofitGithubUsersRepo
import ru.softmine.kotlin_libraries.mvp.presenter.UsersPresenter
import ru.softmine.kotlin_libraries.mvp.view.UsersView
import ru.softmine.kotlin_libraries.ui.App
import ru.softmine.kotlin_libraries.ui.BackClickListener
import ru.softmine.kotlin_libraries.ui.adapter.UsersRVAdapter
import ru.softmine.kotlin_libraries.ui.image.GlideImageLoader
import ru.softmine.kotlin_libraries.ui.navigation.AndroidScreens

class UsersFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router,
            AndroidScreens())
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
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())

        vb?.recyclerViewUsers?.layoutManager = LinearLayoutManager(context)
        vb?.recyclerViewUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}