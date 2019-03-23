package com.rogerio.xingtest.feature.listRepos.presentation

import com.rogerio.xingtest.core.helpers.Mapper.getGitViewEntity
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.xingtest.services.models.GitRepo
import com.rogerio.xingtest.services.repository.GitReposDataSource
import io.reactivex.Single

class ReposInteractor (private val reposDataSource: GitReposDataSource) {
    fun getRepos(page: Int, size: Int): Single<List<GitRepoViewEntity>> = Single
            .just(Pair(page,size))
            .flatMap {
                reposDataSource.getRepos(it.first, it.second)
            }.map (::mapperToView)
            .onErrorResumeNext {
                Single.error(it)
            }

    private fun mapperToView(items: List<GitRepo>): List<GitRepoViewEntity> = items
        .map {
            getGitViewEntity(it)
        }

}