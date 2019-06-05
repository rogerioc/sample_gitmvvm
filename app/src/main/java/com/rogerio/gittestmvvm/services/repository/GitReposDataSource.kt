package com.rogerio.xingtest.services.repository

import com.rogerio.xingtest.services.models.GitRepo
import io.reactivex.Single

interface GitReposDataSource {
    fun getRepos(page: Int, size: Int): Single<List<GitRepo>>
}