package com.rogerio.gittestmvvm.core.helpers

import com.rogerio.gittestmvvm.db.GitRepoEntity
import com.rogerio.gittestmvvm.feature.listRepos.presentation.model.GitRepoViewEntity
import io.reactivex.functions.Function

class MapperRepoToView : Function<List<GitRepoEntity>, List<GitRepoViewEntity>> {
    override fun apply(t: List<GitRepoEntity>): List<GitRepoViewEntity> =
        t.map {
            GitRepoViewEntity(
                name = it.name,
                description = it.description,
                flag = it.flag,
                repoUrl = it.repoUrl,
                ownerUrl = it.ownerUrl,
                ownerName = it.ownerName
            )

        }
}