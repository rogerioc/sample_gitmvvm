package com.rogerio.gittestmvvm.core.helpers

import com.rogerio.gittestmvvm.db.GitRepoEntity
import com.rogerio.gittestmvvm.services.models.GitRepo
import io.reactivex.functions.Function

class GitRepoToRepoEntity: Function<List<GitRepo>, List<GitRepoEntity>> {
    override fun apply(t: List<GitRepo>): List<GitRepoEntity> =
         t.map {
             GitRepoEntity(
                 id = 0,
                 name = it.fullName ?: "",
                 description = it.description ?: "",
                 ownerName = it.owner?.login ?: "",
                 flag = it.fork ?: true,
                 ownerUrl = it.owner.htmlUrl ?: "",
                 repoUrl = it.htmlUrl ?: ""
             )
         }

}
