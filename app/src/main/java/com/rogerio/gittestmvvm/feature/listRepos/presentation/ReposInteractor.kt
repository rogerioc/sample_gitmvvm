package com.rogerio.gittestmvvm.feature.listRepos.presentation

import com.rogerio.gittestmvvm.core.helpers.MapperRepoToView
import com.rogerio.gittestmvvm.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.gittestmvvm.services.repository.GitReposDataSource
import io.reactivex.Single

class ReposInteractor (
    private val reposDataSource: GitReposDataSource
) {

    fun getRepos(page: Int, size: Int): Single<List<GitRepoViewEntity>> =
        Single
            .just(Pair(page,size))
            .flatMap {
                reposDataSource.getRepos(it.first, it.second)
            }.map (MapperRepoToView())

}