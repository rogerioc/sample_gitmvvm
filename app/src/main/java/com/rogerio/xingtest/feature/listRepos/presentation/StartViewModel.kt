package com.rogerio.xingtest.feature.listRepos.presentation

import androidx.databinding.Observable
import com.rogerio.xingtest.core.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class StartViewModel(private val interactor: ReposInteractor): BaseViewModel(), Observable {

    fun load() {
        compositeDisposable.add(
            interactor.getRepos(1,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d(it.toString(),"Repos List")
                }, {
                    Timber.e(it.toString(),"Repos List error")
                })
        )

    }
}