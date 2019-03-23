package com.rogerio.xingtest.feature.listRepos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders.of
import com.rogerio.xingtest.R
import com.rogerio.xingtest.core.BaseViewModelFactory
import com.rogerio.xingtest.core.ServiceFactory

/**
 * A placeholder fragment containing a simple view.
 */
class StartActivityFragment : Fragment() {
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

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }
}
