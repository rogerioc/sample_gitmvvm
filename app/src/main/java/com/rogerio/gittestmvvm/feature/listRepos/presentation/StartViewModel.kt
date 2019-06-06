package com.rogerio.gittestmvvm.feature.listRepos.presentation

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rogerio.gittestmvvm.R
import com.rogerio.gittestmvvm.core.BaseViewModel
import com.rogerio.gittestmvvm.core.InfoError
import com.rogerio.gittestmvvm.feature.listRepos.presentation.model.GitRepoViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class StartViewModel(private val interactor: ReposInteractor): BaseViewModel(), Observable {

    var repositories: ArrayList<GitRepoViewEntity> =  ArrayList()

    private var page: Int = 0
    private var loading: Boolean = false

    private val _errorMessageEvent = MutableLiveData<InfoError>()
    val errorMessageEvent: LiveData<InfoError>
        get() = _errorMessageEvent
    private val _seledtGitRepo = MutableLiveData<GitRepoViewEntity>()
    val selectedGitRepo: LiveData<GitRepoViewEntity>
        get() = _seledtGitRepo

    private var loadingPosition: Int = 0

    fun start() {
        fetchRepos()
    }

    private fun showLoading(loading: Boolean= true) {
        this.loading = loading
        if(loading) {
            this.repositories.add(GitRepoViewEntity(loading = true))
            loadingPosition = this.repositories.size - 1
        } else {
            this.repositories.removeAt(loadingPosition)
            loadingPosition = 0
        }
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
                        showLoading(false)
                        notifyChange()
                    }, {
                        Timber.e(it.toString(), "Repos List error")
                        showLoading(false)
                        _errorMessageEvent.postValue(InfoError( error = R.string.default_error))
                    })
            )
        }
    }

    fun clickOnGitRepo(gitRepo: GitRepoViewEntity) {
        _seledtGitRepo.postValue(gitRepo)
    }
}