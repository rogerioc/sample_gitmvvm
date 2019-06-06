package com.rogerio.gittestmvvm.services.repository

import android.content.Context
import com.rogerio.gittestmvvm.core.DataBase
import com.rogerio.gittestmvvm.core.helpers.GitRepoToRepoEntity
import com.rogerio.gittestmvvm.db.GitRepoEntity
import com.rogerio.gittestmvvm.services.IGitService
import io.reactivex.Single

class GitReposRepository(
    private val service: IGitService,
    private val context: Context
) : GitReposDataSource {

    override fun getRepos(page: Int, size: Int): Single<List<GitRepoEntity>> =
        DataBase.getDatabase(context).repoDao().get((page - 1) * size, size)
            .flatMap {
                if (it.isEmpty()) {
                    service.getRepos(page, size)
                        .map(GitRepoToRepoEntity())
                        .map {
                            DataBase.getDatabase(context).repoDao().insertAll(it)
                            it
                        }
                } else {
                    Single.just(it)
                }
            }
}

