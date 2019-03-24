package com.rogerio.xingtest.feature.listRepos.presentation.dialog

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rogerio.xingtest.core.BaseViewModel
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity

class UrlChooseDialogViewModel(private val gitRepoViewEntity: GitRepoViewEntity?): BaseViewModel() {
    val _event = MutableLiveData<String>()
    public val event: LiveData<String>
        get() = _event


    fun onOwnerClicked() {
        _event.postValue(gitRepoViewEntity?.ownerUrl?.let { it })
    }

    fun onRepoClicked() {
        _event.postValue(gitRepoViewEntity?.repoUrl?.let { it })
    }


}