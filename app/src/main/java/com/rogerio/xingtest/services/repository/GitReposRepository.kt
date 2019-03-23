package com.rogerio.xingtest.services.repository

import com.rogerio.xingtest.services.IGitService
import com.rogerio.xingtest.services.models.GitRepo
import io.reactivex.Single

class GitReposRepository(private val service: IGitService): GitReposDataSource {

    override fun getRepos(page: Int, size: Int): Single<List<GitRepo>> {
        return service.getRepos(page, size)
    }
}