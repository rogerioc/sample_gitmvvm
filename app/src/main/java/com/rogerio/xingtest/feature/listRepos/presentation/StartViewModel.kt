package com.rogerio.xingtest.feature.listRepos.presentation

import androidx.databinding.Observable
import com.rogerio.xingtest.core.BaseViewModel
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class StartViewModel(private val interactor: ReposInteractor): BaseViewModel(), Observable {

    public var repositories: List<GitRepoViewEntity> = emptyList()
    fun load() {
        compositeDisposable.add(
            interactor.getRepos(1,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d(it.toString(),"Repos List")
                    this.repositories = it
                    notifyChange()
                }, {
                    Timber.e(it.toString(),"Repos List error")
                })
        )

    }
}