package com.rogerio.xingtest.feature.listRepos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rogerio.xingtest.R
import com.rogerio.xingtest.core.BaseViewModelFactory
import com.rogerio.xingtest.core.ServiceFactory
import com.rogerio.xingtest.core.customs.endless
import com.rogerio.xingtest.databinding.FragmentStartBinding
import com.rogerio.xingtest.feature.listRepos.presentation.dialog.UrlChooseDialog
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * A placeholder fragment containing a simple view.
 */
class StartActivityFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    private val interactor: ReposInteractor by lazy {
        ReposInteractor(ServiceFactory.repositoryFactory())
    }

    private val viewModel: StartViewModel by lazy {
        of(
            this,
            BaseViewModelFactory { StartViewModel(interactor) }
        ).get(StartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.start()
        val layoutManager = LinearLayoutManager(context)
        reposlist.layoutManager = layoutManager
        reposlist.hasFixedSize()
        reposlist.adapter = ReposAdapter()
        reposlist.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        reposlist.endless { viewModel.fetchRepos() }
        viewModel.errorMessageEvent.observe(this, Observer {
            Toast.makeText(this@StartActivityFragment.context, getString(it.error),Toast.LENGTH_LONG).show()
        })

        viewModel.selectedGitRepo.observe(this, Observer {
            val urlChooseDialog = UrlChooseDialog.newInstance(it)
            urlChooseDialog.show(fragmentManager, UrlChooseDialog.DIALOG_TAG)
        })

        (reposlist.adapter as ReposAdapter).onClickRepo = {
            viewModel.clickOnGitRepo(it)
        }
    }

    override fun onStart() {
        super.onStart()

    }
}
