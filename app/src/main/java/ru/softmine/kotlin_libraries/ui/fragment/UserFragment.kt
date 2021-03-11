package ru.softmine.kotlin_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.softmine.kotlin_libraries.databinding.FragmentUserBinding
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubUser
import ru.softmine.kotlin_libraries.mvp.presenter.UserPresenter
import ru.softmine.kotlin_libraries.mvp.view.UserView
import ru.softmine.kotlin_libraries.ui.App
import ru.softmine.kotlin_libraries.ui.BackClickListener

class UserFragment : MvpAppCompatFragment(), UserView, BackClickListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(githubUser: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, githubUser)
            }
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val githubUser = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(githubUser, App.instance.router)
    }

    private var vb: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setLogin(text: String) {
        vb?.textViewLogin?.text = text
    }

    override fun backPressed() = presenter.backClick()
}