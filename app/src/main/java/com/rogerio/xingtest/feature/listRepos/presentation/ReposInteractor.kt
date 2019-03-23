package com.rogerio.xingtest.feature.listRepos.presentation

import com.rogerio.xingtest.services.models.GitRepo
import com.rogerio.xingtest.services.repository.GitReposDataSource
import io.reactivex.Single

class ReposInteractor (private val reposDataSource: GitReposDataSource) {
    fun getRepos(page: Int, size: Int): Single<List<GitRepo>> {
        return reposDataSource.getRepos(page, size)
    }
}