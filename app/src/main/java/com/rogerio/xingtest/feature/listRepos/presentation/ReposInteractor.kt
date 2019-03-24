package com.rogerio.xingtest.feature.listRepos.presentation

import android.content.Context
import com.rogerio.xingtest.core.DataBase
import com.rogerio.xingtest.core.helpers.Mapper.getGitViewEntity
import com.rogerio.xingtest.core.helpers.Mapper.gitRepoEntityToGitViewEntity
import com.rogerio.xingtest.core.helpers.Mapper.gitViewEntityToGitRepoEntity
import com.rogerio.xingtest.db.GitRepoEntity
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity
import com.rogerio.xingtest.services.models.GitRepo
import com.rogerio.xingtest.services.repository.GitReposDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReposInteractor (private val reposDataSource: GitReposDataSource,
                       private val context: Context) {

    fun getRepos(page: Int, size: Int): Single<List<GitRepoViewEntity>> =
        Single
        .just(Pair(page,size))
        .flatMap {
            reposDataSource.getRepos(it.first, it.second)
        }.map (::mapperToView)
        .flatMap {
            //var reposEntity = it.map(::gitViewEntityToGitRepoEntity)
            //DataBase.getDatabase(context).repoDao().insertAll(reposEntity)
            Single.just(it)
        }
        .onErrorResumeNext {
            Single.error(it)
        }
//        DataBase.getDatabase(context).repoDao().get(page, size)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map(::mapperRepoToView)
//            .flatMap {
//                Single.just(it)
//            }.onErrorResumeNext(
//                Single
//                .just(Pair(page,size))
//                .flatMap {
//                    reposDataSource.getRepos(it.first, it.second)
//                }.map (::mapperToView)
//                .flatMap {
//                    var reposEntity = it.map(::gitViewEntityToGitRepoEntity)
//                    DataBase.getDatabase(context).repoDao().insertAll(reposEntity)
//                    Single.just(it)
//                }
//                .onErrorResumeNext {
//                    Single.error(it)
//                }
//            )

    private fun mapperToView(items: List<GitRepo>): List<GitRepoViewEntity> = items
        .map {
            getGitViewEntity(it)
        }
    private fun mapperRepoToView(items: List<GitRepoEntity>): List<GitRepoViewEntity> = items
        .map {
            gitRepoEntityToGitViewEntity(it)
        }

}