package com.rogerio.xingtest.feature.listRepos.presentation

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rogerio.xingtest.R
import com.rogerio.xingtest.core.BaseViewModel
import com.rogerio.xingtest.core.InfoError
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class StartViewModel(private val interactor: ReposInteractor): BaseViewModel(), Observable {

    public var repositories: ArrayList<GitRepoViewEntity> =  ArrayList()
    private var page: Int = 0
    private var loading: Boolean = false

    private val _errorMessageEvent = MutableLiveData<InfoError>()
    val errorMessageEvent: LiveData<InfoError>
        get() = _errorMessageEvent

    fun start() {
        fetchRepos()
    }


    private fun showLoading() {

    }

    fun fetchRepos() {
        if(!loading) {
            page += 1
            loading = true
            showLoading()
            compositeDisposable.add(
                interactor.getRepos(page, 10)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Timber.d(it.toString(), "Repos List")
                        this.repositories.addAll(it)
                        loading = false
                        notifyChange()
                    }, {
                        Timber.e(it.toString(), "Repos List error")
                        _errorMessageEvent.postValue(InfoError( error = R.string.default_error))
                        loading = false
                    })
            )
        }
    }
}