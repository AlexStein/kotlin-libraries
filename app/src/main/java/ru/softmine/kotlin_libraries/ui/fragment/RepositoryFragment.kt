package ru.softmine.kotlin_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.kotlin_libraries.databinding.FragmentRepositoryBinding
import ru.softmine.kotlin_libraries.mvp.model.entity.GithubRepo
import ru.softmine.kotlin_libraries.mvp.presenter.RepositoryPresenter
import ru.softmine.kotlin_libraries.mvp.view.RepositoryView
import ru.softmine.kotlin_libraries.ui.App
import ru.softmine.kotlin_libraries.ui.BackClickListener


class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackClickListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepo) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    private var vb: FragmentRepositoryBinding? = null

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepo>(REPOSITORY_ARG) as GithubRepo
        RepositoryPresenter(App.instance.router, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun init() {}

    override fun setId(text: String) {
        vb?.tvId?.text = text
    }

    override fun setTitle(text: String) {
        vb?.tvTitle?.text = text
    }

    override fun setDescription(text: String) {
        vb?.tvDescription?.text = text
    }

    override fun backPressed() = presenter.backPressed()
}