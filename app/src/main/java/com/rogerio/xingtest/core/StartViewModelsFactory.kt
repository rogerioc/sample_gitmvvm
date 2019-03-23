package com.rogerio.xingtest.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogerio.xingtest.feature.listRepos.presentation.ReposInteractor
import com.rogerio.xingtest.feature.listRepos.presentation.StartViewModel

//class StartViewModelsFactory(
//    private val interactor: ReposInteractor
//):
//    ViewModelProvider.NewInstanceFactory() {
//        fun <T: StartViewModel> create(modelClass:Class<T>): T {
//            return StartViewModel(interactor) as T
//        }
//    }

class BaseViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}