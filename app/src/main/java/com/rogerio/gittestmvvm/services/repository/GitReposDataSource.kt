package com.rogerio.gittestmvvm.services.repository

import com.rogerio.gittestmvvm.db.GitRepoEntity
import com.rogerio.gittestmvvm.services.models.GitRepo
import io.reactivex.Single

interface GitReposDataSource {
    fun getRepos(page: Int, size: Int): Single<List<GitRepoEntity>>
}